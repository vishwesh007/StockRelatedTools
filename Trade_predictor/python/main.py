import livedatacollection
import datacollector
from kite_trade_debug import *
import entoken

enctoken = entoken.enctoken
kite = KiteApp(enctoken=enctoken)

print(kite.ltp("NSE:RELIANCE"))