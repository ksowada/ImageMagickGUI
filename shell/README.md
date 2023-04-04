# Auto Enhance Picture(s) for web

Image magick is a Linux-based tool used for purpose to enhance multiple images in a stack, so download image magick and use this tool
- resize
- auto correct color
- reduce noise

TODO: ImageMagick may not be called well from java, searching alternatives like XnConvert

## installation
- requires [Image Magick](https://imagemagick.org/)
- install it (at linux make it executable and add it to your PATH-environment variable) to execute it from any path
- set a environment var `MAGICK_HOME` pointing to `magick` program
- *TODO (accept parameter or env-var also)*

## usage
- put your images into a special folder and launch the command
- use auto / normalize or custom commands from command line

### parameters
TODO install --help
- **-i:{file}** Input File Path
- **-o:{file}** Output File Path
- **-auto:{auto-parameter}** some auto options
  some major automatic photo enhancements, all following vars may be overwritten
    - quality=80
    - size: width=987 
- **-size:w{pixels}** set a new size, controlled by
  - **w** width
- **-format:[jpg/webp]** defaults to jpg
  
