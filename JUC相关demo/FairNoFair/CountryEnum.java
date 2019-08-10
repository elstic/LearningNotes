package 锁.FairNoFair;



public enum CountryEnum {
    ONE(1,"齐"),TWO(2,"楚"),THREE(3,"燕"),FOUR(4,"赵"),FIVE(5,"魏"),SIX(6,"韩");

    private Integer retCode;
       private String retMessage;

       public static CountryEnum  forEachEnum(int index){
           CountryEnum [] countryEnums =   CountryEnum.values();
           for (CountryEnum  cEnum:countryEnums) {
                    if (index==cEnum.getRetCode())
                        return cEnum;
           }
           return   null;
       }


    CountryEnum(Integer retCode, String retMessage) {
        this.retCode = retCode;
        this.retMessage = retMessage;
    }

    public Integer getRetCode() {
        return retCode;
    }

    public String getRetMessage() {
        return retMessage;
    }
}
