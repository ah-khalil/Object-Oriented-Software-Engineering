#include "MainWindow.h"
#include <iostream>

/**
 * Constructor. We must initialise the GUI here, which includes creating and 
 * adding the GUI "widgets" and setting up callback functions.
 */
MainWindow::MainWindow(Album* newAlbum) 
:   imageWidget(), // Special C++ syntax for field initialisation: "field(constructor args)"
    prevBtn("Previous"),
    nextBtn("Next"),
    toolbar(Gtk::BUTTONBOX_START)
{
    album = newAlbum;
    
    // Set the window title (set_title is inherited from Gtk::Window).
    set_title("Gtk Image Viewer"); 
    
    // Set up the structure of the GUI window
    add(vbox); // from Gtk::Window
    vbox.add(toolbar);
    vbox.add(imageWidget);
    vbox.add(captionWidget);
    toolbar.add(prevBtn);
    toolbar.add(nextBtn);
    
    // Set up nextBtnHandler to be called when nextBtn is clicked, and similarly for prevBtn.
    prevBtn.signal_clicked().connect(sigc::mem_fun(*this, &MainWindow::prevBtnHandler));
    nextBtn.signal_clicked().connect(sigc::mem_fun(*this, &MainWindow::nextBtnHandler));
    
    
    // Fix this code so that it actually displays the initial (first) image.
    imageWidget.set("[Initial image filename]");
    captionWidget.set_text("[Initial image caption]");
    
    
    // Tell all widgets to display themselves (inherited from Gtk::Window).
    show_all();
}

/**
 * Destructor. Destroy the album object.
 */
MainWindow::~MainWindow()
{    
    delete album;
}

/**
 * Retrieves the album by reference.
 */
Album* MainWindow::getAlbum() const
{
    return album;
}

/**
 * Called to handle "previous" button clicks.
 */
void MainWindow::prevBtnHandler()
{
    // Fix this code so that it actually displays the previous image.
    imageWidget.set("[Previous image filename]");
    captionWidget.set_text("[Previous image caption]");
}

/**
 * Called to handle "next" button clicks.
 */
void MainWindow::nextBtnHandler()
{
    // Fix this code so that it actually displays the next image.
    imageWidget.set("[Next image filename]");
    captionWidget.set_text("[Next image caption]");
}

