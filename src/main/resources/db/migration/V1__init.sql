CREATE EXTENSION IF NOT EXISTS "pgcrypto";

CREATE TABLE project (
    id UUID PRIMARY KEY DEFAULT gen_random_uuid(),
    created_at TIMESTAMP WITH TIME ZONE DEFAULT CURRENT_TIMESTAMP,
    updated_at TIMESTAMP WITH TIME ZONE,
    created_by VARCHAR(255),
    updated_by VARCHAR(255),
    project_name VARCHAR(255) NOT NULL,
    statut VARCHAR(50),
    description TEXT,
    progress INTEGER,
    date_debut TIMESTAMP WITH TIME ZONE,
    date_fin TIMESTAMP WITH TIME ZONE
);

CREATE TABLE epic (
    id UUID PRIMARY KEY DEFAULT gen_random_uuid(),
    created_at TIMESTAMP WITH TIME ZONE DEFAULT CURRENT_TIMESTAMP,
    updated_at TIMESTAMP WITH TIME ZONE,
    created_by VARCHAR(255),
    updated_by VARCHAR(255),
    epic_name VARCHAR(255) NOT NULL,
    desc TEXT,
    statut VARCHAR(50),
    priority VARCHAR(50),
    progress INTEGER,
    date_debut TIMESTAMP WITH TIME ZONE,
    date_fin TIMESTAMP WITH TIME ZONE,
    temp_passe INTEGER,
    project_id UUID REFERENCES project(id) ON DELETE CASCADE
);

CREATE TABLE sprint (
    id UUID PRIMARY KEY DEFAULT gen_random_uuid(),
    created_at TIMESTAMP WITH TIME ZONE DEFAULT CURRENT_TIMESTAMP,
    updated_at TIMESTAMP WITH TIME ZONE,
    created_by VARCHAR(255),
    updated_by VARCHAR(255),
    sprint_name VARCHAR(255) NOT NULL,
    desc TEXT,
    statut VARCHAR(50),
    priority VARCHAR(50),
    progress INTEGER,
    date_debut TIMESTAMP WITH TIME ZONE,
    date_fin TIMESTAMP WITH TIME ZONE,
    temp_passe INTEGER,
    project_id UUID REFERENCES project(id) ON DELETE CASCADE
);

CREATE TABLE users (
    id UUID PRIMARY KEY DEFAULT gen_random_uuid(),
    created_at TIMESTAMP WITH TIME ZONE DEFAULT CURRENT_TIMESTAMP,
    updated_at TIMESTAMP WITH TIME ZONE,
    created_by VARCHAR(255),
    updated_by VARCHAR(255),
    name VARCHAR(255) NOT NULL
);

CREATE TABLE story (
    id UUID PRIMARY KEY DEFAULT gen_random_uuid(),
    created_at TIMESTAMP WITH TIME ZONE DEFAULT CURRENT_TIMESTAMP,
    updated_at TIMESTAMP WITH TIME ZONE,
    created_by VARCHAR(255),
    updated_by VARCHAR(255),
    story_name VARCHAR(255) NOT NULL,
    desc TEXT,
    statut VARCHAR(50),
    priority VARCHAR(50),
    pts INTEGER,
    date_debut TIMESTAMP WITH TIME ZONE,
    date_fin TIMESTAMP WITH TIME ZONE,
    time_left INTEGER,
    epic_id UUID REFERENCES epic(id) ON DELETE CASCADE,
    sprint_id UUID REFERENCES sprint(id) ON DELETE SET NULL
);

CREATE TABLE task (
    id UUID PRIMARY KEY DEFAULT gen_random_uuid(),
    created_at TIMESTAMP WITH TIME ZONE DEFAULT CURRENT_TIMESTAMP,
    updated_at TIMESTAMP WITH TIME ZONE,
    created_by VARCHAR(255),
    updated_by VARCHAR(255),
    task_name VARCHAR(255) NOT NULL,
    desc TEXT,
    statut VARCHAR(50),
    priority VARCHAR(50),
    date_debut TIMESTAMP WITH TIME ZONE,
    time_left INTEGER,
    time_estimate INTEGER,
    tag VARCHAR(100),
    story_id UUID REFERENCES story(id) ON DELETE CASCADE,
    sprint_id UUID REFERENCES sprint(id) ON DELETE SET NULL,
    assigned_user_id UUID REFERENCES users(id) ON DELETE SET NULL
);

CREATE TABLE subtask (
    id UUID PRIMARY KEY DEFAULT gen_random_uuid(),
    created_at TIMESTAMP WITH TIME ZONE DEFAULT CURRENT_TIMESTAMP,
    updated_at TIMESTAMP WITH TIME ZONE,
    created_by VARCHAR(255),
    updated_by VARCHAR(255),
    name VARCHAR(255) NOT NULL,
    desc TEXT,
    statut VARCHAR(50),
    task_id UUID REFERENCES task(id) ON DELETE CASCADE
);

CREATE TABLE profile (
    id UUID PRIMARY KEY DEFAULT gen_random_uuid(),
    created_at TIMESTAMP WITH TIME ZONE DEFAULT CURRENT_TIMESTAMP,
    updated_at TIMESTAMP WITH TIME ZONE,
    created_by VARCHAR(255),
    updated_by VARCHAR(255),
    name VARCHAR(255) NOT NULL
);

CREATE TABLE privilege (
    id UUID PRIMARY KEY DEFAULT gen_random_uuid(),
    created_at TIMESTAMP WITH TIME ZONE DEFAULT CURRENT_TIMESTAMP,
    updated_at TIMESTAMP WITH TIME ZONE,
    created_by VARCHAR(255),
    updated_by VARCHAR(255),
    name VARCHAR(255) NOT NULL,
    privilege VARCHAR(255)
);

CREATE TABLE user_profile (
    user_id UUID NOT NULL REFERENCES users(id) ON DELETE CASCADE,
    profile_id UUID NOT NULL REFERENCES profile(id) ON DELETE CASCADE,
    PRIMARY KEY (user_id, profile_id)
);

CREATE TABLE profile_privilege (
    profile_id UUID NOT NULL REFERENCES profile(id) ON DELETE CASCADE,
    privilege_id UUID NOT NULL REFERENCES privilege(id) ON DELETE CASCADE,
    PRIMARY KEY (profile_id, privilege_id)
);

CREATE TABLE user_project (
    project_id UUID NOT NULL REFERENCES project(id) ON DELETE CASCADE,
    user_id UUID NOT NULL REFERENCES users(id) ON DELETE CASCADE,
    PRIMARY KEY (project_id, user_id)
);

CREATE TABLE user_group (
    id UUID PRIMARY KEY DEFAULT gen_random_uuid(),
    created_at TIMESTAMP WITH TIME ZONE DEFAULT CURRENT_TIMESTAMP,
    updated_at TIMESTAMP WITH TIME ZONE,
    created_by VARCHAR(255),
    updated_by VARCHAR(255),
    name VARCHAR(255) NOT NULL,
    project_id UUID REFERENCES project(id) ON DELETE CASCADE
);

CREATE TABLE user_group_profile (
    group_id UUID NOT NULL REFERENCES user_group(id) ON DELETE CASCADE,
    user_id UUID NOT NULL REFERENCES users(id) ON DELETE CASCADE,
    PRIMARY KEY (group_id, user_id)
);
