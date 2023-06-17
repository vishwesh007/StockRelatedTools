import datetime

def perform_action(timestamp, stock_price, market_cap):
    time = datetime.datetime.fromtimestamp(timestamp)
    hour = time.hour
    minute = time.minute

    if hour > 10 or (hour == 10 and minute >= 30):
        if stock_price >= 1.125 * stock_price and market_cap > 50000:
            # Sell the stock in intraday for 10:30 and above
            print("Sell the stock in intraday")

        else:
            # Perform other action for 10:30 and above
            print("Perform other action for 10:30 and above")

    elif hour == 12 and minute == 35:
        # Perform action for 12:35 timestamp
        print("Action for 12:35")

    elif hour == 13 and minute == 0:
        # Perform action for 13:00 timestamp
        print("Action for 13:00")

    elif hour == 14 and minute == 5:
        # Perform action for 14:05 timestamp
        print("Action for 14:05")

    elif hour == 14 and minute == 40:
        # Close all open positions for 14:40
        print("Close all open positions")

    else:
        # Default action if the timestamp doesn't match any specified time
        print("No action defined for this timestamp")

# Example usage
timestamp = 1686715200  # Replace with your desired timestamp
stock_price = 100  # Replace with the actual stock price
market_cap = 60000  # Replace with the actual market cap
perform_action(timestamp, stock_price, market_cap)
