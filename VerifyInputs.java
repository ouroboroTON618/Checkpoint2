import java.util.List;

public class VerifyInputs {

    public static boolean verifyDates(String date) {

        boolean check = true;
        // YYYY/MM/DD
        // 0123456789
        
      

        if (date.length() == INFO_LEN.DATE.getLength()) {
        	String dateWithoutSlash = date.replace("/", "");
        	System.out.println(dateWithoutSlash);
                for (int i = 0; i < dateWithoutSlash.length(); i++) {
                   
                	 char ch = dateWithoutSlash.charAt(i);
                	    if (!Character.isDigit(ch)) {
                	        check = false;
                	        break;
                	    }
                }
        } else {
            check = false;
            System.out.println("Input needs to be a date in the format of YYYY/MM/DD");
        }

        return check;
    }

    public static boolean verifyYear(String year) {

        return verifyNoInput(year, INFO_LEN.YEAR.getLength(), false);
    }

    public static boolean verifyMemberIDSizeNo(String memberID) {

        // member id is of length:

        return verifyNoInput(memberID, INFO_LEN.MEMBER_ID.getLength(), false);
    }

    public static boolean verifyRentalSizeNo(String rentalNo) {

        // member id is of length:

        return verifyNoInput(rentalNo, INFO_LEN.RENTAL_NO.getLength(), false);
    }

    public static boolean VerifyMemberID(String memberID) {
        boolean check1 = verifyMemberIDSizeNo(memberID);

        boolean check2 = false;
        if (check1) {
            ResultPackage result = QueryManager.getMember(Integer.parseInt(memberID));
            if (result != null) {
                check2 = VerifyInputs.verifyTableDataSingle(result, memberID, true);
            }
        }
        return check1 && check2;
    }

    public static boolean VerifyRentalNo(String rentalNo) {
        boolean check1 = verifyMemberIDSizeNo(rentalNo);

        boolean check2 = false;
        if (check1) {
            ResultPackage result = QueryManager.getRentalNos();
            if (result != null) {
                check2 = VerifyInputs.verifyTableDataSingle(result, rentalNo, true);
            }
        }
        return check1 && check2;
    }

    public static boolean VerifyEquipmentType(String type) {
        boolean check1 = verifyStringOnly(type);
        boolean check2 = false;
        if (check1 && Main.databaseEnabled) {
            ResultPackage result = QueryManager.getEquipmentTypes();
            if (result != null) {
                check2 = VerifyInputs.verifyTableDataSingle(result, type, true);

            }
        }
        return check1 && check2;
    }

    public static boolean verifySerialNo(String SerialNo) {
        return verifyNoInput(SerialNo, INFO_LEN.SERIAL_NO.getLength(), false);
    }

    public static boolean verifySerialNoExist(String serialNo) {
        boolean check1 = verifySerialNo(serialNo);

        boolean check2 = false;
        if (check1) {
            ResultPackage result = QueryManager.getSerialNo();
            if (result != null) {
                check2 = VerifyInputs.verifyTableDataSingle(result, serialNo, true);
            }
        }
        return check1 && check2;
    }

    public static boolean verifySerialNoExistOnly(String serialNo) {

        ResultPackage result = QueryManager.getSerialNo();
        if (result != null) {
            return VerifyInputs.verifyTableDataSingle(result, serialNo, true);
        } else {
            return false;
        }

    }

    public static boolean verifyCond(String cond) {
        if (verifyStringOnly(cond)) {
            switch (cond.toLowerCase()) {
                case "good":
                    return true;

                case "fair":
                    return true;

                case "poor":
                    return true;
                default:
                    System.out.println("Input needs to be good, fair or poor");
                    return false;
            }
        } else {
            System.out.println("Please only enter A-Z characters.");
            return false;
        }
    }

    public static boolean verifyNoInput(String input, int length, boolean lenPass) {
        boolean check = true;

        if(input.length() != 0) {
	        if (input.length() == length || lenPass) {
	            for (int i = 0; i < input.length(); i++) {
	            	char ch = input.charAt(i);
	        	    if (!Character.isDigit(ch)) {
	        	        check = false;
	        	        break;
	        	    }
	            }
	        } else {
	            if (input.length() != 0) {
	                System.out.println("Input needs to be a number of length " + length);
	            }
	            check = false;
	        }
        }else {
        	return false;
        }
        return check;
    }

    public static boolean verifyYNInput(String line) {
        if (line.toLowerCase().charAt(0) == 'y') {
            return true;
        } else {
            return false;
        }
    }

    public static boolean verifyStringOnly(String line) {

        if (line.length() > 0) {
            String regex = "^[a-zA-Z]+$";
            boolean isValid = line.matches(regex);
            if (!isValid) {
                System.out.println("Input can only contain characters.");
            }
            return isValid;
        } else {
            return false;
        }
    }

    /**
     * 
     * @param result
     * @param check
     * @param exist
     *               check to see if the value exists inside the table
     * @return
     */

    public static boolean verifyTableDataSingle(ResultPackage result, String check, boolean exist) {
        List<List<String>> data = result.getTableData();
        for (List<String> row : data) {
            for (String value : row) {
                if (check.equals(value)) {
                    return true;
                }
            }
        }

        if (exist) {
            System.out.println(check + " was not found in the database. Please Try Again");
        }
        return false;
    }

    public static boolean verifyRentalStatus(String rentalStatus) {
        return rentalStatus.equals("Available") || rentalStatus.equals("Unavailable");
    }

    public static boolean verifyModelExists(String modelNo) {

        ResultPackage result = QueryManager.getModelNo();
        return verifyTableDataSingle(result, modelNo, true);

    }

}
