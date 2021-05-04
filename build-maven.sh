#!/usr/bin/env bash

set -eo pipefail

modules=(api-gateway-service config-service discovery-service  customer-service order-service product-service)

for module in "${modules[@]}"; do
  mvn spring-boot:build-image -DskipTests -f "${module}"
done

# mvn spring-boot:build-image -DskipTests -f product-service