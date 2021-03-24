package app.model.font;

import suite.suite.Subject;

import static suite.suite.$uite.$;

public class FontManager {

    Subject $fonts = $();

    public LoadedFont getFont(Font font) {
        var $f = $fonts.in(font).set();
        if($f.absent()) {
            $f.set(new LoadedFont(font));
        }
        return $f.asExpected();
    }

    public BackedFont getFont(Font font, float size) {
        var $lf = $fonts.in(font).set();
        if($lf.absent()) {
            $lf.set(new LoadedFont(font));
        }
        int quantizedSize = quantizeSize(size);
        var $bf = $lf.in(quantizedSize).set();
        if($bf.absent()) {
            $bf.set(new BackedFont($lf.asExpected(), quantizedSize));
        }
        return $bf.asExpected();
    }

    private int quantizeSize(float s) {
        return ((int)Math.ceil(s) + 31) / 32 * 32;
    }
}
