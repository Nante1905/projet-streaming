package server.converter;

import java.io.File;
import java.io.IOException;

import ws.schild.jave.Encoder;
import ws.schild.jave.EncoderException;
import ws.schild.jave.InputFormatException;
import ws.schild.jave.MultimediaObject;
import ws.schild.jave.encode.AudioAttributes;
import ws.schild.jave.encode.EncodingAttributes;
import ws.schild.jave.encode.VideoAttributes;

public class Tomkv {
    public static File convert(File source) throws IllegalArgumentException, InputFormatException, EncoderException, IOException {
        // File source = new File(filename);
        File target = File.createTempFile("send", ".mkv");

        AudioAttributes audio = new AudioAttributes();
        audio.setCodec("libmp3lame");
        audio.setBitRate(256000);
        audio.setChannels(1);
        audio.setSamplingRate(22050);

        VideoAttributes video = new VideoAttributes();
        video.setCodec("mpeg4");
        video.setBitRate(10000000);
        video.setFrameRate(60);

        EncodingAttributes attrs = new EncodingAttributes();
        attrs.setOutputFormat("matroska");
        attrs.setAudioAttributes(audio);
        attrs.setVideoAttributes(video);

        Encoder encoder = new Encoder();
        encoder.encode(new MultimediaObject(source), target, attrs);

        return target;
    }
}

