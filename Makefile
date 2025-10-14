database-up:
	docker compose -f src/main/java/dev/gabrielt/microservices/authentication_service/infra/database/docker-compose.yml up -d

database-down:
	docker compose -f src/main/java/dev/gabrielt/microservices/authentication_service/infra/database/docker-compose.yml stop