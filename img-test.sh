# just for test the main script and use as example for script
# ./img-auto-web.sh -i:sample/20230311_014840.jpg -o:out/20230311_014840.b.jpg -auto:all -size:w1597
export MAGICK_HOME="/home/karlito/Apps/magick-7.1.1.4"
rm -r out
mkdir out
java ImgAutoWeb -i:sample/20230311_014840.jpg -o:out/20230311_014840.b.jpg -auto -size:w1597