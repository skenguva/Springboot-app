#!/bin/bash
# wait-for-it.sh

host="$1"
shift
port="$1"
shift

until nc -z "$host" "$port"; do
  echo "Waiting for MySQL to be ready..."
  sleep 2
done

echo "MySQL is ready, starting Spring Boot app..."
exec "$@"

