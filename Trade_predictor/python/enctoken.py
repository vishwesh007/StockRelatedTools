from kite_trade import *
import datetime
import time


user_id = ""       # Login Id
password = ""      # Login password
twofa = ""         # Login Pin or TOTP

enctoken = get_enctoken(user_id, password, twofa)

print(enctoken)