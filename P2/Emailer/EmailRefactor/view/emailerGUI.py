import gtk
import message

class AmazingWindow(gtk.Window):
    """ The GUI interface for our simple emailer. """

    PADDING = 10

    def __init__(self):
        """ Construct a new AmazingWindow. """

        gtk.Window.__init__(self) # Superclass constructor
        self.set_title("Py Emailer")

        # Important GUI widgets
        self.to = gtk.Entry()
        self.subject = gtk.Entry()
        self.content = gtk.TextView()
        self.sendButton = gtk.Button("Send")

        # Add scroll bars to the message area.
        scrollArea = gtk.ScrolledWindow()
        scrollArea.add(self.content)
        scrollArea.set_policy(gtk.POLICY_AUTOMATIC, gtk.POLICY_AUTOMATIC)
        scrollArea.set_shadow_type(gtk.SHADOW_ETCHED_IN)

        # Fit all the widgets onto the window.
        self.vbox = gtk.VBox()
        self.add(self.vbox)
        self.addLabelledWidget("To",      self.to)
        self.addLabelledWidget("Subject", self.subject)
        self.vbox.pack_start(scrollArea, expand = True, fill = True)
        self.vbox.pack_start(self.sendButton, expand = False)

        # Set up callbacks for the close and send buttons
        self.connect("delete-event", gtk.main_quit)
        self.sendButton.connect("clicked", self.send)

        # Make everything visible
        self.show_all()


    def addLabelledWidget(self, text, widget):
        """ Add a widget preceded by a text label. """
        label = gtk.Label(text)
        label.set_mnemonic_widget(widget)
        hbox = gtk.HBox()
        hbox.pack_start(label, expand = False, padding = AmazingWindow.PADDING)
        hbox.pack_start(widget, expand = True, fill = True)
        self.vbox.pack_start(hbox, expand = False)

if __name__ == "__main__":
    # Setup the window and run everything.
    window = AmazingWindow()
    gtk.main()
