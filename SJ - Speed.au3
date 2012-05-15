#include <GUIConstantsEx.au3>
#include <StaticConstants.au3>


Opt("GUIOnEventMode", 1)
GUICreate("SJ Speed", 120, 60)
$baar = GUICtrlCreateProgress ( 10, 10, 100, 20)
$text = GUICtrlCreateLabel ( " Km/h", 10, 40, 100, 20, $SS_CENTER)
GUICtrlSetData($baar, "0")
GUISetState(@SW_SHOW)
GUISetOnEvent($GUI_EVENT_CLOSE, "Stang")

Func Stang()
    Exit
EndFunc

While 1
$data = InetRead ("http://www.ombord.info/api/xml/position/", 1)
$data = BinaryToString($data)
#$data = '<position><time type="double">1337091456</time><latitude type="double">55.609523</latitude><longitude type="double">13.002023</longitude><altitude type="double">4.9</altitude><speed type="double">50.0</speed><cmg type="double">0.0</cmg><satellites type="integer">11</satellites></position>'
$datadelar = StringSplit ($data, "</speed>",1)
$datadelar2 = StringSplit ($datadelar[1] , '<speed type="double">',1)
GUICtrlSetData($baar, Int(Number($datadelar2[2])*1.8))
GUICtrlSetData($text, Int(Number($datadelar2[2])*3.6) & " Km/h")
sleep(2000)
WEnd
