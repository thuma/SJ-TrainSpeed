#AutoIt3Wrapper_icon=train.ico
;Include extra au3 info.
#include <GUIConstantsEx.au3>
#include <StaticConstants.au3>

; Create GUI
Opt("GUIOnEventMode", 1)
GUICreate("SJ Speed", 120, 60)
$baar = GUICtrlCreateProgress ( 10, 10, 100, 20)
$text = GUICtrlCreateLabel ( " Km/h", 10, 40, 100, 20, $SS_CENTER)
GUICtrlSetData($baar, "0")
GUISetState(@SW_SHOW)
GUISetOnEvent($GUI_EVENT_CLOSE, "Stang")

; Exit if close
Func Stang()
    Exit
EndFunc

; Main function loop
While 1
; Get onboard page with data and convert to string.
$data = InetRead ("http://www.ombord.info/api/xml/position/", 1)
$data = BinaryToString($data)
; Split out the speed data (the data is resported in m/s):
$datadelar = StringSplit ($data, "</speed>",1)
$datadelar2 = StringSplit ($datadelar[1] , '<speed type="double">',1)
; Update the data in the GUI.
GUICtrlSetData($baar, Int(Number($datadelar2[2])*1.8))
GUICtrlSetData($text, Int(Number($datadelar2[2])*3.6) & " Km/h")
; Sleep for 2 sec before next fetch.
sleep(2000)
WEnd
