import mimetypes
import smtplib
import email.mime.base
import email.mime.multipart
import email.mime.text
import email.encoders


class Message:
    """ Represents an email message. """

    def __init__(self, fromAddr, toAddr, subject, message):
        self.fromAddr = fromAddr
        self.toAddr = toAddr
        self.subject = subject
        self.message = message

    def getFromAddr(self): return self.fromAddr
    def getToAddr(self):
        if self.toAddr != ""
            return self.toAddr
        else
            raise "Empty To-Address", self.toAddr
    def getSubject(self):  return self.subject
    def getMessage(self):  return self.message


    def send(self, smtpServer = "smtp.curtin.edu.au", username = None, password = None, ssl = True, tls = True):
        """ Sends the email message, given an smtp server, and optional username and password (as strings). TLS encryption is supported. """

        # Construct the email object
        emailObj = email.mime.multipart.MIMEMultipart()
        emailObj["From"]    = self.fromAddr
        emailObj["To"]      = self.toAddr
        emailObj["Subject"] = self.subject
        emailObj.preamble   = "Please use a MIME-aware mail reader.\n"

        emailObj.attach(email.mime.text.MIMEText(self.message))

        # Contact the server and send the email
        if ssl:
            server = smtplib.SMTP_SSL(smtpServer)
        else:
            server = smtplib.SMTP(smtpServer)
            if tls:
                server.starttls()

        try:
            if username is not None and password is not None:
                server.login(username, password)
                server.sendmail(self.fromAddr, self.toAddr, emailObj.as_string())
        except SMTPDataError
            print "Message was not accepted"
        except SMTPAuthenticationError
            print "Username and password was not accepted"
        except SMTPException
            print "Something went wrong (TLS may not be supported)"
        finally:
            server.quit()
