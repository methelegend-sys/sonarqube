@echo off

set DC_VERSION="latest"
set DC_DIRECTORY=%USERPROFILE%\OWASP-Dependency-Check
SET DC_PROJECT="dependency-check scan: %CD%"
set DATA_DIRECTORY="%DC_DIRECTORY%\data"
set REPORT_DIRECTORY="%CD%\report"

IF NOT EXIST %DATA_DIRECTORY% (
    echo Initially creating persistent directory: %DATA_DIRECTORY%
    mkdir %DATA_DIRECTORY%
)
IF NOT EXIST %REPORT_DIRECTORY% (
    echo Initially creating persistent directory: %REPORT_DIRECTORY%
    mkdir %REPORT_DIRECTORY%
)

rem Make sure we are using the latest version
docker pull owasp/dependency-check:%DC_VERSION%

docker run --rm ^
    --volume %CD%:/src ^
    --volume %DATA_DIRECTORY%:/usr/share/dependency-check/data ^
    --volume %REPORT_DIRECTORY%:/report ^
    owasp/dependency-check:%DC_VERSION% ^
    --scan /src ^
    --format "ALL" ^
    --project "%DC_PROJECT%" ^
    --out /report
    rem Use suppression like this: (where /src == %CD%)
    rem --suppression "/src/security/dependency-check-suppression.xml"
