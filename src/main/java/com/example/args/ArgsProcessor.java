package com.example.args;

public class ArgsProcessor {

    public static final String FILE_MODE = "-f";
    public static final String SYMBOLS_MODE = "-s";
    public static final String FILE_TYPES = "-t";
    public static final String LENGTH_MODE = "-l";
    public static final String RECUSRIVE = "-r";
    public static final String PREFIX_MODE = "-p";

    public static Args process(String[] programArgs) {
        Args args = new Args();

        String flag = null;
        for (String programArg : programArgs) {
            switch (programArg) {
                case RECUSRIVE:
                    args.setRecursive(true);
                    break;
                case FILE_MODE:
                case LENGTH_MODE:
                case SYMBOLS_MODE:
                case FILE_TYPES:
                case PREFIX_MODE:
                    flag = programArg;
                    break;
                default:
                    if (flag == null) break;
                    else if (FILE_MODE.equals(flag) && args.getPath() == null) args.setPath(programArg);
                    else if (SYMBOLS_MODE.equals(flag)) args.addRandFlag(programArg);
                    else if (FILE_TYPES.equals(flag)) args.addFileTypes(programArg);
                    else if (LENGTH_MODE.equals(flag) && args.getLength() == null) {
                        args.setLength(Integer.parseInt(programArg));
                    } else if (PREFIX_MODE.equals(flag) && args.getPre() == null) args.setPre(programArg);
            }
        }
        return args;
    }
}
