import os


packages = ['beautifulsoup4', 'matplotlib', 'numpy','requests','sklearn']

for package in packages:
    try:
        __import__(package)
    except ImportError:
        os.system(f'python -m pip install {package}')






# try:
#     import requests
# except ImportError:
#     os.system('python -m pip install requests')
# try:
#     import matplotlib
# except ImportError:
#     os.system('python -m pip install matplotlib')
# try:
#     import beautifulsoup
# except ImportError:
#     os.system('python -m pip install beautifulsoup4')
# try:
#     import numpy
# except ImportError:
#     os.system('python -m pip install numpy')




