#!/usr/bin/env bash

set -eo pipefail

modules=(api-gateway-service config-service discovery-service  customer-service order-service product-service)

for module in "${modules[@]}"; do
  docker build -t "${module}:latest" "${module}"
done

#  docker build -t "product-service:latest" product-service