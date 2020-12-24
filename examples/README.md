# Genitor Examples

Genitor DSL script examples.

## Run

### Prerequisites

- Docker
- Vagrant
- Ansible

First, build all projects:

```bash
./gradlew assemble
```

Then, start containers (PostgreSQL/ZooKeeper/Kafka) and Genitor Master:

```bash
docker-compose up -d
./gradlew :genitor-master:bootRun # This command is blocking
```

Finally, start VMs:

```bash
KAFKA_HOST=$(ip a | grep -oE 'inet \b([0-9]{1,3}\.){3}[0-9]{1,3}\b' | sed -n 2p | awk '{print $2}'):9093
cd vagrant
vagant up --provision
```
