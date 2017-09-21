import message

    def send(self, widget):
        """ Send the message. """

        # Construct settings object
        settingsObj = settings.Settings()

        # Retrieve message content
        buf = self.content.get_buffer()

        # Construct message object
        messageObj = message.Message(fromAddr = settingsObj.getAddress(),
                                    toAddr   = self.to.get_text(),
                                    subject  = self.subject.get_text(),
                                    message  = buf.get_text(buf.get_start_iter(), buf.get_end_iter()))

        # Set up information dialog box
        statusDialog = gtk.MessageDialog(self)
        statusDialog.set_markup("Sending message...")
        statusDialog.show()

        # Send message, based on settings & message objects.
        messageObj.send(smtpServer = settingsObj.getSmtpServer(),
                        username = settingsObj.getUsername(),
                        password = settingsObj.getPassword(),
                        ssl = settingsObj.isSSLEnabled(),
                        tls = settingsObj.isTLSEnabled())

        statusDialog.set_markup("Message sent successfully.")

    def getAddress(self):    return self.address
    def getUsername(self):   return self.username
    def getPassword(self):   return self.password
    def getSmtpServer(self): return self.smtpServer
    def isSSLEnabled(self):  return self.useSSL
    def isTLSEnabled(self):  return self.useTLS
