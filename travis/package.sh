#!/bin/bash

mvn clean install cobertura:cobertura

bash <(curl -s https://codecov.io/bash)
