from kite_trade import *
import datetime
import time



user_id = "KWS986"       # Login Id
password = "Vish4050835"      # Login password
twofa = "417052"         # Login Pin or TOTP


enctoken = get_enctoken(user_id, password, twofa)

print(enctoken)

file_path = "enctoken.txt"  # Replace with your desired file path

# Open the file in write mode
with open(file_path, 'w') as file:
    # Write data to the file
    file.write(enctoken)
    

# The file is automatically closed after exiting the 'with' block
