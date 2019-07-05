import { browser, ElementFinder, element, by, protractor } from "protractor";
import { expect } from "chai";

let EC = protractor.ExpectedConditions;
let elem = require('./wsrc5100UnlReceivalEnq.json');



export class wsrc5100UnlReceivalEnq {

   private woolNumberKey:any;
   private clipCodeKey:any;
   private clipBrand:any;


	    constructor() {
	
	        browser.manage().timeouts().implicitlyWait(2000);
	        browser.manage().timeouts().pageLoadTimeout(5000);
	    }
    
    

     	 public async woolNumberKeyMethodTxt(woolNumberKey_txt: string) { 
          await element(by.xpath(elem.input.woolNumberKey)).sendKeys(woolNumberKey_txt);   
       }
         public async woolNumberKeyMethodTxtValue() {    
          await element(by.xpath(elem.input.woolNumberKey)).getAttribute('value').then(async function (actrm) {   
                           this.var_woolNumberKey = actrm;  
              });
      }
     	 public async clipCodeKeyMethodTxt(clipCodeKey_txt: string) { 
          await element(by.xpath(elem.input.clipCodeKey)).sendKeys(clipCodeKey_txt);   
       }
         public async clipCodeKeyMethodTxtValue() {    
          await element(by.xpath(elem.input.clipCodeKey)).getAttribute('value').then(async function (actrm) {   
                           this.var_clipCodeKey = actrm;  
              });
      }
     	 public async clipBrandMethodTxt(clipBrand_txt: string) { 
          await element(by.xpath(elem.input.clipBrand)).sendKeys(clipBrand_txt);   
       }
         public async clipBrandMethodTxtValue() {    
          await element(by.xpath(elem.input.clipBrand)).getAttribute('value').then(async function (actrm) {   
                           this.var_clipBrand = actrm;  
              });
      }
    

    	public async clickAtaction_button_1Button() 
        {
           await element(by.xpath(elem.button.action_button_1)).click(); 
        }

    	public async clickAtFilterButton() 
        {
           await element(by.xpath(elem.button.Filter)).click(); 
        }

    	public async clickAtfKey_03Button() 
        {
           await element(by.xpath(elem.button.fKey_03)).click(); 
        }

    	public async clickAtfKey_05Button() 
        {
           await element(by.xpath(elem.button.fKey_05)).click(); 
        }

    	public async clickAtcontinueButtonButton() 
        {
           await element(by.xpath(elem.button.continueButton)).click(); 
        }

   
}
