# script.xml - File definiton

refer to [README.md](README.md) for project info
## Example

```
<?xml version="1.0" encoding="UTF-8"?>
<ImageMagickScript>
	<Title>AutoPhotoEnhancements with ImageMagick</Title>
    <Author>Karl Sowada</Author>
    <Date>13.01.2018</Date>
    <Version>0.1.0</Version>
    <Script name="Identify" outFile="no">
        <Command>magick</Command>
        <Operator>identify</Operator>
    </Script>
    <Script name="CombineToGIF" packInput="yes" outExt="gif">
        <Example>magick 2019-01-15*.png -set delay 100 berlin2.gif</Example>
        <Command>magick</Command>
        <Setting>-set delay %i
            <Help>give delay between GIF Frames in multiples of 1/100s = 10ms</Help>
            <Default>4</Default>
        </Setting>
    </Script>
</ImageMagickScript>
```
## `<ImageMagickScript>` Root-Element

Root-Element in XML-Files, give the Container for the Script's.

defines some Meta-Information of the File

### `<Title>`

give Title of File

### `<Author>`

give Name of Author

### `<Date>`

add actual Date of Edit

### `<Version>`

set Version of Script-File

## `<Script>` Major Script Container

this Tag descibes one Transformation. One File may describe one or more Script's

### @name

an easy handy name for the script
f.e. `name=“dummy”`

### @outFile (optional)
type `outFile=“no”`, if no output is used.
Default `“yes”`

### @packInput (optional)
use `packInput=“yes”` to allow all selected Items to be combined in one execution of transformation
Default `“no”`

### @outExt (optional)
use some certain file-extension for the output-file. 
f.e. u can transform a row of pictures to a `outExt=“GIF”` for a animated GIF

## `<Script>` Content

### `<Example>` (optional)
just an handy example of an working test

### `<Help>` (optional)

select some help text for the given variable use cleartext or in HTML-Style 

`<Default>` (optional)

give some fixed default value

### `<Command>`
the command line executable

### `<Setting>` (optional)
Image Setting

### `<Operator>` (optional)
Image Operator

### `<ChannelOperator>` (optional)
Image Channel Operator

### `<SequenceOperator>` (optional)
Image Sequence Operator

### `<Geometry>` (optional)

Image Geometry

## Variables in XML-Definiton
Inbetween some of above tags, you can include the following.

### `<Help>` (optional)

select some help text for the given variable use cleartext or in HTML-Style 

### `%i` Integer (optional)

use `%i` for an undetermined variable Integer-Number Value, allows the User to adjust the Transformation

### `%s` String (optional)

use `%s` for an undetermined variable Text-String Value, allows the User to adjust the Transformation

### `<Default>` (optional)

give some fixed default value





