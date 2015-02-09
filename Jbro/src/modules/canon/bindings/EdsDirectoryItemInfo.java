package modules.canon.bindings;

import com.sun.jna.NativeLong;
import com.sun.jna.Pointer;
import com.sun.jna.Structure;

/**
 * <i>native declaration : EDSDK\Header\EDSDKTypes.h</i><br>
 * This file was autogenerated by <a
 * href="http://jnaerator.googlecode.com/">JNAerator</a>,<br>
 * a tool written by <a href="http://ochafik.com/">Olivier Chafik</a> that <a
 * href="http://code.google.com/p/jnaerator/wiki/CreditsAndLicense">uses a few
 * opensource projects.</a>.<br>
 * For help, please visit <a
 * href="http://nativelibs4java.googlecode.com/">NativeLibs4Java</a> , <a
 * href="http://rococoa.dev.java.net/">Rococoa</a>, or <a
 * href="http://jna.dev.java.net/">JNA</a>.
 */
public class EdsDirectoryItemInfo extends Structure {

    /// C type : EdsUInt32
    public NativeLong size;
    /// C type : EdsBool
    public int isFolder;
    /// C type : EdsUInt32
    public NativeLong groupID;
    /// C type : EdsUInt32
    public NativeLong option;
    /// C type : EdsChar[256]
    public byte[] szFileName = new byte[256];
    /// C type : EdsUInt32
    public NativeLong format;

    public EdsDirectoryItemInfo() {
        super();
        initFieldOrder();
    }

    protected void initFieldOrder() {
        setFieldOrder( new String[] { "size", "isFolder", "groupID", "option",
                                     "szFileName", "format" } );
    }

    /**
     * @param size C type : EdsUInt32<br>
     * @param isFolder C type : EdsBool<br>
     * @param groupID C type : EdsUInt32<br>
     * @param option C type : EdsUInt32<br>
     * @param szFileName C type : EdsChar[256]<br>
     * @param format C type : EdsUInt32
     */
    public EdsDirectoryItemInfo( final NativeLong size, final int isFolder,
                                 final NativeLong groupID,
                                 final NativeLong option,
                                 final byte szFileName[],
                                 final NativeLong format ) {
        super();
        this.size = size;
        this.isFolder = isFolder;
        this.groupID = groupID;
        this.option = option;
        if ( szFileName.length != this.szFileName.length ) {
            throw new IllegalArgumentException( "Wrong array size !" );
        }
        this.szFileName = szFileName;
        this.format = format;
        initFieldOrder();
    }

    public EdsDirectoryItemInfo( final Pointer pointer ) {
        super( pointer );
        read();
    }

    public static class ByReference extends EdsDirectoryItemInfo implements Structure.ByReference {

    };

    public static class ByValue extends EdsDirectoryItemInfo implements Structure.ByValue {

    };
}
