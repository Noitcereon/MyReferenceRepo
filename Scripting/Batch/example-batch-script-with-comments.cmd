@ECHO OFF
SETLOCAL
:: 'REM' or double colon is a comment. It is short for 'Remark'
:: SETLOCAL makes all the variables (until ENDLOCAL) in this script only available inside this script.
:: By default, variables are global to your entire command prompt session.

:: A good resource about batch scripts: https://www.tutorialspoint.com/batch_script/index.htm


:: Set SoapUI home to check if SOAP UI is installed and to use a SoapUI scripts later.
SET SOAPUI_HOME=C:\Program Files\SmartBear\SoapUI-5.7.0\bin

IF NOT EXIST "%SOAPUI_HOME%\." (
    ECHO "Path '%SOAPUI_HOME%' does not exist"
    ECHO "Please update this script and set SOAPUI_HOME to the correct path."

    :: 'EXIT /B 1' stands for "exit with error"
    EXIT /B 1
)

ECHO SOAPUI_HOME=%SOAPUI_HOME%
SET OUTPUT_FOLDER=target

:: RMDIR documentation: https://learn.microsoft.com/en-us/windows-server/administration/windows-commands/rmdir
RMDIR /S /Q %OUTPUT_FOLDER%
MKDIR %OUTPUT_FOLDER%

ECHO Building mock services...
:: buildMock function is defined at the bottom of this script
CALL :buildMock ReferenceInformationV2Mock, mockReferenceInformationV2PortBinding, ReferenceInformationV2Service-soapui-project.xml
CALL :buildMock EUListeMeddelelseSendMock, mockEUListeMeddelelseSendServiceSoapBinding, EUListeMeddelelseSendMock-soapui-project.xml
CALL :buildMock EUListeMeddelelseSendIMock, mockEUListeMeddelelseSendIServiceSoapBinding, EUListeMeddelelseSendIMock-soapui-project.xml
CALL :buildMock AngivelseFrekvensForholdSamlingHentMock, mockAngivelseFrekvensForholdSamlingHentServiceBinding, AngivelseFrekvensForholdSamlingHentMock-soapui-project.xml
CALL :buildMock RegistreringForholdOpdaterMock, mockRegistreringForholdOpdaterServiceBinding, RegistreringForholdOpdaterMock-soapui-project.xml
CALL :buildMock VirksomhedStamOplysningerSamlingHent2008NS, mockVirksomhedStamOplysningSamlingHentServiceBinding, VirksomhedStamOplysningerSamlingHent2008NS-soapui-project.xml
CALL :buildMock VirksomhedPligterHentMock, mockVirksomhedPligterHentServiceBinding, VirksomhedPligterHentMock-soapui-project.xml
CALL :buildMock VirksomhedNavnOplysningHentMock, mockVirksomhedNavnOplysningHentServiceBinding, VirksomhedNavnOplysningHentMock-soapui-project.xml
CALL :buildMock EUListeMeddelelseModtagOMock, mockEUListeMeddelelseModtagOServiceSoapBinding, EUListeMeddelelseModtagOMock-soapui-project.xml
CALL :buildMock VirksomhedBrancheForholdKlassifikationHentMock, mockVirksomhedBrancheForholdKlassifikationHentServiceBinding, VirksomhedBrancheForholdKlassifikationHentMock-soapui-project.xml
CALL :buildMock VirksomhedKontaktOplysningSamlingHentMock, mockVirksomhedKontaktOplysningSamlingHentServiceBinding, VirksomhedKontaktOplysningSamlingHentMock-soapui-project.xml

ECHO Finished building mock services. You can find them in the %OUTPUT_FOLDER% folder.
ECHO Listing all war files in %OUTPUT_FOLDER% folder:
DIR %OUTPUT_FOLDER%\*.war
:: ENDLOCAL Destroys all local variables made by this script.
ENDLOCAL

:: 'EXIT /B 0' stands for EXIT with success.
:: THE EXIT on the line below is there to prevent the buildMock to run without arguments.
EXIT /B 0
:: ':buildMocks' is a function/label
:buildMock
    ECHO -----------------------------------------------------------------------------
    ECHO Arg1 is %1
    ECHO Arg2 is %2
    ECHO Arg3 is %3
    ECHO Generating output directory as %OUTPUT_FOLDER%\%1
    MKDIR %OUTPUT_FOLDER%\%1

    :: The '^' symbol indicates continuation of the same line on the next line.
    :: To see documentation for the -d, -f, -e flags, try calling the wargenerator.bat (without arguments) in a console by yourself.
    ECHO Calling %SOAPUI_HOME%\wargenerator.bat with -d, -f, and -e flags + 1 argument
    CALL "%SOAPUI_HOME%\wargenerator.bat" ^
     -d %OUTPUT_FOLDER%\%1 ^
     -f %OUTPUT_FOLDER%\%1.war ^
     -e http://localhost:8080/%1/%2 ^
      src\main\resources\%3
EXIT /B 0
