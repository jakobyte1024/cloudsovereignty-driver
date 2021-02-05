#!/bin/bash
set -e

az login --service-principal \
    -u $ARM_CLIENT_ID \
    -p $ARM_CLIENT_SECRET \
    --tenant $ARM_TENANT_ID \
    --allow-no-subscriptions > /dev/null

# don't hardcode tc-showcase-test here TODO
SUPPLIERFQDN=$(az keyvault secret show --vault-name "vault-tc-showcase-test" --name "${STAGE}-supplierdomain-azurefqdn" --query value -o tsv)
ORDERFQDN=$(az keyvault secret show --vault-name "vault-tc-showcase-test" --name "${STAGE}-orderdomain-azurefqdn" --query value -o tsv)
MANUFQDN=$(az keyvault secret show --vault-name "vault-tc-showcase-test" --name "${STAGE}-manufacturedomain-azurefqdn" --query value -o tsv)


# put values to Github's env stage
# this not a safe solution

echo "SUPPLIERFQDN=$SUPPLIERFQDN" >> $GITHUB_ENV
echo "ORDERFQDN=$ORDERFQDN" >> $GITHUB_ENV
echo "MANUFQDN=$MANUFQDN" >> $GITHUB_ENV