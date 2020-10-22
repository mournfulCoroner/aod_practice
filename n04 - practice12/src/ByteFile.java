import java.io.*;

public class ByteFile {
    private static String fileInp;
    private static String fileOut;

    public ByteFile(String inp, String out) {
        fileInp = inp;
        fileOut = out;
    }

    public void createNewBinary(){
        try {
            FileInputStream reader = new FileInputStream(fileInp);
            FileOutputStream writer = new FileOutputStream(fileOut);
            BufferedOutputStream bos = new BufferedOutputStream(writer);
            DataOutputStream out = new DataOutputStream(writer);
            byte[] b = reader.readAllBytes();
            out.writeBytes(toBinary(b));
        }
        catch(IOException ex){
            System.out.println(ex.getMessage());
        }
    }

    public void showFile(){
        System.out.println(getBinaryFileData());
    }

    public String getBinaryFileData(){
        String line = "";
        try(FileInputStream reader = new FileInputStream(fileOut)){
            BufferedInputStream buf = new BufferedInputStream(reader);
            byte[] b = reader.readAllBytes();
            line = new String(b);
        }
        catch (IOException ex){
            System.out.println(ex.getMessage());
        }
        return BinaryToString(line);
    }

    public void showStealing(){
        System.out.println("\n==Cars, listed as stolen==");
        String info = getBinaryFileData();
        String[] sections = info.split("\n");
        for (String sec: sections) {
            if(sec.charAt(sec.length()-2) == '1') System.out.println(sec);
        }
    }

    public void checkIsStolen(String number){
        String info = getBinaryFileData();
        String[] sections = info.split("\n");
        for (String sec: sections) {
            if(sec.contains(number)){
                if(sec.charAt(sec.length()-2) == '1') System.out.println("\nThe car with number '" + number
                        + "' is reported stolen");
                else System.out.println("\nThe car with number '" + number
                        + "' is not reported stolen");
                break;
            }
        }
    }

    public String stringToBinary(String text)
    {
        String bString="";
        String temp="";
        for(int i=0;i<text.length();i++)
        {
            temp=Integer.toBinaryString(text.charAt(i));
            for(int j=temp.length();j<8;j++)
            {
                temp="0"+temp;
            }
            bString+=temp+" ";
        }

        System.out.println(bString);
        return bString;
    }
    public String BinaryToString(String binaryCode)
    {
        String[] code = binaryCode.split(" ");
        StringBuilder word= new StringBuilder();
        for(int i=0;i<code.length;i++)
        {
            word.append((char) Integer.parseInt(code[i], 2));
        }
        return word.toString();
    }

    public String toBinary( byte[] bytes )
    {
        StringBuilder sb = new StringBuilder(bytes.length * Byte.SIZE);
        for( int i = 0; i < Byte.SIZE * bytes.length; i++ ) {
            sb.append((bytes[i / Byte.SIZE] << i % Byte.SIZE & 0x80) == 0 ? '0' : '1');
        }
        for(int i = 8; i < sb.length(); i+=9) sb.insert(i, " ");
        return sb.toString();
    }

    public byte[] fromBinary( String s )
    {
        int sLen = s.length();
        byte[] toReturn = new byte[(sLen + Byte.SIZE - 1) / Byte.SIZE];
        char c;
        for( int i = 0; i < sLen; i++ )
            if( (c = s.charAt(i)) == '1' )
                toReturn[i / Byte.SIZE] = (byte) (toReturn[i / Byte.SIZE] | (0x80 >>> (i % Byte.SIZE)));
            else if ( c != '0' )
                throw new IllegalArgumentException();
        return toReturn;
    }
}
