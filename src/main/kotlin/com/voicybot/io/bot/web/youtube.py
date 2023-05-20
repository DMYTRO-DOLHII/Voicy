import sys
from pytube import YouTube
from time import time

if __name__ == "__main__":
    start = time()

    video_url = sys.argv[1]
    yt = YouTube(video_url)
    # Use 360p or 144p because some videos does not support better quality
    stream = yt.streams.filter(res='144p').first()
    stream.download(filename='video.mp4')

    end = time()

    print(f"IT TOOK : {end - start}")
