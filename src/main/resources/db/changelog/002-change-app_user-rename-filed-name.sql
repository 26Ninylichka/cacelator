--liquibase formatted sql
--changeset cacelator:2

ALTER TABLE app_user RENAME COLUMN display_name TO name;
