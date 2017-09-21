import getpass

class Settings:
    """ Contains email settings. """
    
    def __init__(self):
        self.address = raw_input("Your email address: ")
        self.username = raw_input("Username: ")
        self.password = getpass.getpass("Password: ")
        self.smtpServer = raw_input("Your SMTP server: ")
        self.useSSL = False
        self.useTLS = True
        
    def getAddress(self):    return self.address
    def getUsername(self):   return self.username
    def getPassword(self):   return self.password
    def getSmtpServer(self): return self.smtpServer
    def isSSLEnabled(self):  return self.useSSL
    def isTLSEnabled(self):  return self.useTLS
