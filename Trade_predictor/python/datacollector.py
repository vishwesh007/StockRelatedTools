from kite_trade import *
import datetime
import time
import entoken


enctoken = entoken.enctoken
kite = KiteApp(enctoken=enctoken)


# print(kite.margins())
# print(kite.orders())
# print(kite.positions())

instrument_token = 470529
from_datetime = datetime.datetime.now() - datetime.timedelta(days=7)     # From last & days
to_datetime = datetime.datetime.now()
interval = "5minute"
print(kite.historical_data(instrument_token, from_datetime, to_datetime, interval, continuous=False, oi=False))