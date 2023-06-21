from kite_trade import *
import datetime
import time
import entoken

enctoken = entoken.enctoken
# file_path="enctoken.txt"

# with open(file_path,'r') as file:
#     enctoken=file.readline
#enctoken = "Xfivgzafy06/YPzGy1WgVBHE8PS0aEBhGpAqIw6a6Mm88mtFC/zYuTyp8Je/2bGrHD/cWeD17xdEHKpnt/o6oDTDSxUR8PGVaQMTaGn1taZJElcoSPW5jg=="

kite = KiteApp(enctoken=enctoken)
 
input_file_path = r"Trade_predictor\python\nse_values.txt"
output_file_path = r"output_"+str(datetime.date.today())+".txt"
 
# Read the NSE values from the file
with open(input_file_path, 'r') as input_file:
    nse_values = input_file.read().splitlines()
 
end_time = datetime.datetime.now().replace(hour=15, minute=20, second=0, microsecond=0)
 
# Open the output file in append mode
output_file = open(output_file_path, 'a')
 
while datetime.datetime.now() < end_time:
    # Get the LTP (Last Traded Price) for the NSE values using kite.ltp()
    ltp_values = kite.ltp(nse_values)
 
    # Write the LTP values to the output file
    output_file.write(f'{ltp_values}\n')
    output_file.flush()  # Flush the buffer to ensure immediate write
 
    # Print the LTP values
    print(ltp_values)
 
    time.sleep(1)
 
# Close the output file
output_file.close()