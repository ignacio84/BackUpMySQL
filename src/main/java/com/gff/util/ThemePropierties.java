package com.gff.util;

import static com.gff.util.Enviroment.*;
import com.jtattoo.plaf.aluminium.AluminiumLookAndFeel;
import java.util.Properties;
import javax.swing.UIManager;

public class ThemePropierties {

    public ThemePropierties() throws Exception {
        AluminiumLookAndFeel.setCurrentTheme(this.appProps());//APLICA LAS PROPIEDADES AL TEMA
        UIManager.setLookAndFeel("com.jtattoo.plaf.aluminium.AluminiumLookAndFeel");
    }

    private static Properties appProps() {
        Properties props = new Properties();
        props.put("logoString", "");
        props.put("textShadow", "on");
        props.put("windowDecoration", "on");
        props.put("systemTextFont", APP_FONT);
        props.put("controlTextFont", APP_FONT);
        props.put("menuTextFont", APP_FONT);
        props.put("userTextFont", APP_FONT);
        props.put("subTextFont", APP_FONT);
        props.put("windowTitleFont", APP_TITLE_FONT);
        //             props.put("logoString", "my company"); 
//            props.put("licenseKey", "INSERT YOUR LICENSE KEY HERE");
//            props.put("selectionBackgroundColor", "180 240 197"); 
//            props.put("menuSelectionBackgroundColor", "180 240 197"); 
//            props.put("controlColor", "218 254 230");
//            props.put("controlColorLight", "218 254 230");
//            props.put("controlColorDark", "180 240 197"); 
//            props.put("buttonColor", "218 230 254");
//            props.put("buttonColorLight", "255 255 255");
//            props.put("buttonColorDark", "244 242 232");
//            props.put("rolloverColor", "218 254 230"); 
//            props.put("rolloverColorLight", "218 254 230"); 
//            props.put("rolloverColorDark", "180 240 197"); 
//            props.put("windowTitleForegroundColor", "0 0 0");
//            props.put("windowTitleBackgroundColor", "180 240 197"); 
//            props.put("windowTitleColorLight", "218 254 230"); 
//            props.put("windowTitleColorDark", "180 240 197"); 
//            props.put("windowBorderColor", "218 254 230");

        return props;
    }

}
