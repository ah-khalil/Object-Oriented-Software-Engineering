/**
 * A simple image viewer application.
 * Author: David Cooper and ...
 */

#include <iostream>
#include <fstream>
#include <string>
#include <gtkmm.h>

#include "MainWindow.h"
#include "Album.h"

bool readAlbumFile(std::string albumFilename, Album* album);

int main(int argc, char** argv) 
{
    std::string albumFilename;
    
    // Input the album filename.
    std::cout << "Enter album filename: ";
    std::getline(std::cin, albumFilename);
    
    // Initialise GTK.
    Gtk::Main kit(argc, argv);
    
    // Construct an album object.
    Album* album = new Album();
    
    // Read an album file.
    if(readAlbumFile(albumFilename, album))
    {
        // Construct new window object (on the stack; hence no "=" sign and no pointers).
        MainWindow window(album);

        // Run the GUI.
        Gtk::Main::run(window);    
    }
    else
    {
        std::cerr << "Error while reading " << albumFilename << std::endl;
    }
    return 0;
}

/**
 * Reads an album file, given a filename and an Album object. Returns true if
 * successful, or false if the file could not be read.
 */
bool readAlbumFile(std::string albumFilename, Album* album)
{
    // Open file for reading. (c_str() converts a C++ std::string to a C-style char*.)
    std::ifstream file(albumFilename.c_str());
    
    while(file.good()) // 'good' returns true until end-of-file or an error occurs.
    {
        std::string imageFilename, imageCaption;
        
        std::getline(file, imageFilename, ':');
        std::getline(file, imageCaption);
        
        if(imageFilename.size() > 0)
        {
            // Insert your code here to add a new image to the album.
        }
    }
    
    // Return "true" if we reached the end-of-file, meaning success. "false" implies an error.
    return file.eof(); 
}
