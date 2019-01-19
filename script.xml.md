# script.xml - File definiton

refer to [README.md](README.md) for project info

## XML-Definition

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

### Example (optional)

just an handy example of an working test

### Command

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

### `%i` Integer (optimal)

use `%i` for an ungiven variable integer value, to allow the user to adjust the transformation

### `<help>` (optional)

select some help text for the given variable use cleartext in XML-Style 

`<default>` (optional)

give some fixed default value





