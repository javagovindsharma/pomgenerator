import { browser, ElementFinder, element, by, protractor } from "protractor";
import { expect } from "chai";

let EC = protractor.ExpectedConditions;
let elem = require('./wrkPlantEquipment.json');



export class wrkPlantEquipment {

   private equipmentStatus:any;
   private centreCodeKey:any;
   private equipmentTypeCode:any;
   private equipmentLeasor:any;
   private plantEquipmentCode:any;
   private readingType1:any;
   private firstCheckboxId:any;


	    constructor() {
	
	        browser.manage().timeouts().implicitlyWait(2000);
	        browser.manage().timeouts().pageLoadTimeout(5000);
	    }
    
    

     	 public async equipmentStatusMethodTxt(equipmentStatus_txt: string) { 
          await element(by.xpath(elem.input.equipmentStatus)).sendKeys(equipmentStatus_txt);   
       }
         public async equipmentStatusMethodTxtValue() {   
			 
			
          await element(by.xpath(elem.input.equipmentStatus)).getAttribute('value').then(async function (actrm) {   
                           this.var_equipmentStatus = actrm;  
              });
      }
     	 public async centreCodeKeyMethodTxt(centreCodeKey_txt: string) { 
          await element(by.xpath(elem.input.centreCodeKey)).sendKeys(centreCodeKey_txt);   
       }
         public async centreCodeKeyMethodTxtValue() {    
          await element(by.xpath(elem.input.centreCodeKey)).getAttribute('value').then(async function (actrm) {   
                           this.var_centreCodeKey = actrm;  
              });
      }
     	 public async equipmentTypeCodeMethodTxt(equipmentTypeCode_txt: string) { 
          await element(by.xpath(elem.input.equipmentTypeCode)).sendKeys(equipmentTypeCode_txt);   
       }
         public async equipmentTypeCodeMethodTxtValue() {    
          await element(by.xpath(elem.input.equipmentTypeCode)).getAttribute('value').then(async function (actrm) {   
                           this.var_equipmentTypeCode = actrm;  
              });
      }
     	 public async equipmentLeasorMethodTxt(equipmentLeasor_txt: string) { 
          await element(by.xpath(elem.input.equipmentLeasor)).sendKeys(equipmentLeasor_txt);   
       }
         public async equipmentLeasorMethodTxtValue() {    
          await element(by.xpath(elem.input.equipmentLeasor)).getAttribute('value').then(async function (actrm) {   
                           this.var_equipmentLeasor = actrm;  
              });
      }
     	 public async plantEquipmentCodeMethodTxt(plantEquipmentCode_txt: string) { 
          await element(by.xpath(elem.input.plantEquipmentCode)).sendKeys(plantEquipmentCode_txt);   
       }
         public async plantEquipmentCodeMethodTxtValue() {    
          await element(by.xpath(elem.input.plantEquipmentCode)).getAttribute('value').then(async function (actrm) {   
                           this.var_plantEquipmentCode = actrm;  
              });
      }
     	 public async readingType1MethodTxt(readingType1_txt: string) { 
          await element(by.xpath(elem.input.readingType1)).sendKeys(readingType1_txt);   
       }
         public async readingType1MethodTxtValue() {    
          await element(by.xpath(elem.input.readingType1)).getAttribute('value').then(async function (actrm) {   
                           this.var_readingType1 = actrm;  
              });
      }
     	 public async firstCheckboxIdMethodTxt(firstCheckboxId_txt: string) { 
          await element(by.xpath(elem.input.firstCheckboxId)).sendKeys(firstCheckboxId_txt);   
       }
         public async firstCheckboxIdMethodTxtValue() {    
          await element(by.xpath(elem.input.firstCheckboxId)).getAttribute('value').then(async function (actrm) {   
                           this.var_firstCheckboxId = actrm;  
              });
      }
    

    	public async clickAtaction_button_2Button() 
        {
           await element(by.xpath(elem.button.action_button_2)).click(); 
        }

    	public async clickAtaction_button_51Button() 
        {
           await element(by.xpath(elem.button.action_button_51)).click(); 
        }

    	public async clickAtaction_button_RButton() 
        {
           await element(by.xpath(elem.button.action_button_R)).click(); 
        }

    	public async clickAtaction_button_AButton() 
        {
           await element(by.xpath(elem.button.action_button_A)).click(); 
        }

    	public async clickAtaction_button_BButton() 
        {
           await element(by.xpath(elem.button.action_button_B)).click(); 
        }

    	public async clickAtaction_button_NButton() 
        {
           await element(by.xpath(elem.button.action_button_N)).click(); 
        }

    	public async clickAtFilterButton() 
        {
           await element(by.xpath(elem.button.Filter)).click(); 
        }

    	public async clickAtfKey_03Button() 
        {
           await element(by.xpath(elem.button.fKey_03)).click(); 
        }

    	public async clickAtfKey_06Button() 
        {
           await element(by.xpath(elem.button.fKey_06)).click(); 
        }

    	public async clickAtfKey_10Button() 
        {
           await element(by.xpath(elem.button.fKey_10)).click(); 
        }

    	public async clickAtfKey_11Button() 
        {
           await element(by.xpath(elem.button.fKey_11)).click(); 
        }

    	public async clickAtcontinueButtonButton() 
        {
           await element(by.xpath(elem.button.continueButton)).click(); 
        }

   
}
