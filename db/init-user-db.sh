#!/bin/bash
set -e

psql -v ON_ERROR_STOP=1 --username "postgres" --dbname "postgres" <<-EOSQL
    CREATE USER helloworld;
    CREATE DATABASE helloworld;
    GRANT ALL PRIVILEGES ON DATABASE helloworld TO helloworld;
EOSQL