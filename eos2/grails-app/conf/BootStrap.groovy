import org.apache.shiro.crypto.hash.Sha512Hash

import grails.util.GrailsUtil

import org.leaf.eos2.shiro.User
import org.leaf.eos2.shiro.Role

import org.leaf.eos2.admin.Profile
import org.leaf.eos2.admin.RecordType

import org.leaf.eos2.b2b.Department
import org.leaf.eos2.b2b.Staff
import org.leaf.eos2.b2b.Dealer
import org.leaf.eos2.b2b.Industry

import org.leaf.eos2.b2b.Contract
import org.leaf.eos2.b2b.ContractDetail

import org.leaf.eos2.b2b.Bom
import org.leaf.eos2.b2b.BomDetail

import org.leaf.eos2.b2b.Category
import org.leaf.eos2.b2b.Product
import org.leaf.eos2.b2b.ProductCategory

import org.leaf.eos2.ws.ObConfig

class BootStrap {

    def init = { servletContext ->
        switch(GrailsUtil.environment) {
            case "development":
                configureForTest()
                break
        }
    }


	def configureForTest = {
/*init for administrator*/
def profile_admin      = new Profile(name:"Administrator Profile").save(flush:true);

def role_admin = new Role(name:"Admin Role", isAdmin : true)
	//.addToUsers(user_admin)
	.addToPermissions("*:*")
	.save(flush:true);
def user_admin = new User(username: "admin",  passwordHash: new Sha512Hash("admin").toHex(), profile : profile_admin, role : role_admin).save(flush:true);
	
/*init recordType*/
def recordType220 = new RecordType(serialNumber :'220', name:'客户订单', domain:'salesOrder', description:'', isActive:true).save(flush:true)
def recordType221 = new RecordType(serialNumber :'221', name:'特价订单', domain:'salesOrder', description:'', isActive:true).save(flush:true)
def recordType224 = new RecordType(serialNumber :'224', name:'项目订单', domain:'salesOrder', description:'', isActive:true).save(flush:true)

/*init department*/
def profile_department = new Profile(name:"Department Profile").save(flush:true)
def profile_dealer = new Profile(name:"Dealer Profile").save(flush:true)
/*init department*/
def dept3002 = new Department(serialNumber:3002  ,name:'商务部', type : 'C', parentDept : null, description:'L1',isActive:true).save(flush:true)

def dept3200 = new Department(serialNumber:3200  ,name:'华南大区', type : 'R', parentDept : dept3002, description:'',isActive:true).save(flush:true)
def dept3210 = new Department(serialNumber:3210  ,name:'广州办事处', type : 'B', parentDept : dept3200, description:'',isActive:true).save(flush:true)
def dept3220 = new Department(serialNumber:3220  ,name:'东莞办事处', type : 'B', parentDept : dept3200, description:'',isActive:true).save(flush:true)

def dept3300 = new Department(serialNumber:3300  ,name:'华东大区', type : 'R', parentDept : dept3002, description:'',isActive:true).save(flush:true)
def dept3310 = new Department(serialNumber:3310  ,name:'上海工控办事处', type : 'B', parentDept : dept3300, description:'',isActive:true).save(flush:true)

def dept3400 = new Department(serialNumber:3400  ,name:'华中大区', type : 'R', parentDept : dept3002, description:'',isActive:true).save(flush:true)
def dept3450 = new Department(serialNumber:3450  ,name:'合肥办事处', type : 'B', parentDept : dept3400, description:'',isActive:true).save(flush:true)

def dept3500 = new Department(serialNumber:3500  ,name:'华北大区', type : 'R', parentDept : dept3002, description:'',isActive:true).save(flush:true)
def dept3510 = new Department(serialNumber:3510  ,name:'北京办事处', type : 'B', parentDept : dept3500,description:'', isActive:true).save(flush:true)

def dept3800 = new Department(serialNumber:3800  ,name:'深圳大区', type : 'R', parentDept : dept3002, description:'',isActive:true).save(flush:true)
def dept3830 = new Department(serialNumber:3830  ,name:'深圳工控办事处', type : 'B', parentDept : dept3800, description:'',isActive:true).save(flush:true)


def roleDepartmentApproval = new Role(name:"Department Role[Approval]", isAdmin : false)
	.addToPermissions("index:*")
	.addToPermissions("nav:*")
	.addToPermissions("auth:*")
	.addToPermissions("welcome:*")
	
	.addToPermissions("contract:index")
	.addToPermissions("contract:list")
	.addToPermissions("contract:show")
	
	.addToPermissions("salesOrder:index")
	.addToPermissions("salesOrder:list")
	.addToPermissions("salesOrder:show")
	
	.addToPermissions("salesOrder220:show")
	.addToPermissions("salesOrder221:show")
	.addToPermissions("salesOrder224:show")
	
	.addToPermissions("workflowApproval:check")
	.addToPermissions("workflowApproval:approval")
	
	.addToPermissions("salesOrder220Ajax:getDealerDepartment")
	.addToPermissions("salesOrder220Ajax:workflowHistory")
	.addToPermissions("salesOrder220Ajax:outBoundMessage")
	
	.addToPermissions("salesOrder221Ajax:getDealerDepartment")
	.addToPermissions("salesOrder221Ajax:workflowHistory")
	.addToPermissions("salesOrder221Ajax:outBoundMessage")

	.addToPermissions("salesOrder224Ajax:getDealerDepartment")
	.addToPermissions("salesOrder224Ajax:workflowHistory")
	.addToPermissions("salesOrder224Ajax:outBoundMessage")
	
	.addToPermissions("report:run")
	.addToPermissions("report:view")
	
	.addToPermissions("outBound:index")
	.addToPermissions("outBound:list")
	.addToPermissions("outBound:show")
	.addToPermissions("outBound:resurrect")
	.addToPermissions("outBound:showXml")
	
	.addToPermissions("creditControl:index")
	.addToPermissions("creditControl:list")
	.addToPermissions("creditControl:show")
	
	.addToPermissions("search:index")
	.addToPermissions("search:search")
	.addToPermissions("search:searchProduct")
	.addToPermissions("search:searchContract")
	.addToPermissions("search:searchSalesOrder")
	
	.addToPermissions("menu:mySalesOrder")
	.addToPermissions("menu:b2b")
	.addToPermissions("menu:workflow")
	.addToPermissions("menu:mySalesOrder")
	.addToPermissions("menu:mySalesOrderList")
	.addToPermissions("menu:reportList")
	.addToPermissions("menu:outBoundList")
	.addToPermissions("menu:search")
	.addToPermissions("menu:resetMyPassword")
	
	.addToPermissions("resetMyPassword:resetPassword")
	.addToPermissions("resetMyPassword:reset")
	
	.save(flush:true)

def roleDepartmentReadonly = new Role(name:"Department Role[Readonly]", isAdmin : false)
	.addToPermissions("index:*")
	.addToPermissions("nav:*")
	.addToPermissions("auth:*")
	.addToPermissions("welcome:*")
	
	.addToPermissions("contract:index")
	.addToPermissions("contract:list")
	.addToPermissions("contract:show")
	
	.addToPermissions("salesOrder:index")
	.addToPermissions("salesOrder:list")
	.addToPermissions("salesOrder:show")
	
	.addToPermissions("salesOrder220:show")
	.addToPermissions("salesOrder221:show")
	.addToPermissions("salesOrder224:show")
		
	.addToPermissions("salesOrder220Ajax:getDealerDepartment")
	.addToPermissions("salesOrder220Ajax:workflowHistory")
	.addToPermissions("salesOrder220Ajax:outBoundMessage")
	
	.addToPermissions("salesOrder221Ajax:getDealerDepartment")
	.addToPermissions("salesOrder221Ajax:workflowHistory")
	.addToPermissions("salesOrder221Ajax:outBoundMessage")

	.addToPermissions("salesOrder224Ajax:getDealerDepartment")
	.addToPermissions("salesOrder224Ajax:workflowHistory")
	.addToPermissions("salesOrder224Ajax:outBoundMessage")

	.addToPermissions("report:run")
	.addToPermissions("report:view")
	
	.addToPermissions("outBound:index")
	.addToPermissions("outBound:list")
	.addToPermissions("outBound:show")
	.addToPermissions("outBound:showXml")

	.addToPermissions("search:index")
	.addToPermissions("search:search")
	.addToPermissions("search:searchProduct")
	.addToPermissions("search:searchContract")
	.addToPermissions("search:searchSalesOrder")
	
	.addToPermissions("creditControl:index")
	.addToPermissions("creditControl:list")
	.addToPermissions("creditControl:show")
	
	.addToPermissions("menu:mySalesOrder")
	.addToPermissions("menu:b2b")
	.addToPermissions("menu:workflow")
	.addToPermissions("menu:mySalesOrder")
	.addToPermissions("menu:mySalesOrderList")
	.addToPermissions("menu:reportList")
	.addToPermissions("menu:outBoundList")
	.addToPermissions("menu:search")
	.addToPermissions("menu:resetMyPassword")
	
	.addToPermissions("resetMyPassword:resetPassword")
	.addToPermissions("resetMyPassword:reset")

	.save(flush:true)

/*group member*/
def user3002_1 = new User(username: "C3002",  passwordHash: new Sha512Hash("C3002").toHex(), profile:profile_department, department : dept3002, role : roleDepartmentApproval).save(flush:true)

def user3200_1 = new User(username: "R3200",  passwordHash: new Sha512Hash("R3200").toHex(), profile:profile_department, department : dept3200, role : roleDepartmentReadonly).save(flush:true)
def user3220_1 = new User(username: "B3220",  passwordHash: new Sha512Hash("B3220").toHex(), profile:profile_department, department : dept3220, role : roleDepartmentApproval).save(flush:true)

def user3300_1 = new User(username: "R3300",  passwordHash: new Sha512Hash("R3300").toHex(), profile:profile_department, department : dept3300, role : roleDepartmentReadonly).save(flush:true)
def user3310_1 = new User(username: "B3310",  passwordHash: new Sha512Hash("B3310").toHex(), profile:profile_department, department : dept3310, role : roleDepartmentApproval).save(flush:true)

def user3400_1 = new User(username: "R3400",  passwordHash: new Sha512Hash("R3400").toHex(), profile:profile_department, department : dept3400, role : roleDepartmentReadonly).save(flush:true)
def user3450_1 = new User(username: "B3450",  passwordHash: new Sha512Hash("B3450").toHex(), profile:profile_department, department : dept3450, role : roleDepartmentApproval).save(flush:true)

def user3500_1 = new User(username: "R3500",  passwordHash: new Sha512Hash("R3500").toHex(), profile:profile_department, department : dept3500, role : roleDepartmentReadonly).save(flush:true)
def user3510_1 = new User(username: "B3510",  passwordHash: new Sha512Hash("B3510").toHex(), profile:profile_department, department : dept3510, role : roleDepartmentApproval).save(flush:true)


/*staff*/
def staff10210 = new Staff(serialNumber : '10210', name :'李晓辉', department : dept3830).save(flush:true)
def staff10854 = new Staff(serialNumber : '10854', name :'王波', department : dept3310).save(flush:true)
def staff13073 = new Staff(serialNumber : '13073', name :'刘妙', department : dept3210).save(flush:true)

   
        def role_dealer = new Role(name:"Dealer Role", isAdmin : false)
        .addToPermissions("index:*")
        .addToPermissions("menu:mySalesOrderList")
        .addToPermissions("auth:*")
        .addToPermissions("welcome:*")

        .addToPermissions("contract:index")
        .addToPermissions("contract:list")
        .addToPermissions("contract:show")

        .addToPermissions("salesOrder:index")
        .addToPermissions("salesOrder:list")
        .addToPermissions("salesOrder:show")
        .addToPermissions("salesOrder:edit")
        .addToPermissions("salesOrder:delete")

        .addToPermissions("salesOrder220:create")
        .addToPermissions("salesOrder220:save")
        .addToPermissions("salesOrder220:show")
        .addToPermissions("salesOrder220:edit")
        .addToPermissions("salesOrder220:update")
        .addToPermissions("salesOrder220:delete")

        .addToPermissions("salesOrder221:create")
        .addToPermissions("salesOrder221:save")
        .addToPermissions("salesOrder221:show")
        .addToPermissions("salesOrder221:edit")
        .addToPermissions("salesOrder221:update")
        .addToPermissions("salesOrder221:delete")


        .addToPermissions("salesOrder224:create")
        .addToPermissions("salesOrder224:save")
        .addToPermissions("salesOrder224:show")
        .addToPermissions("salesOrder224:edit")
        .addToPermissions("salesOrder224:update")
        .addToPermissions("salesOrder224:delete")


        .addToPermissions("salesOrderAjax:*")
		.addToPermissions("salesOrder220Ajax:*")
		.addToPermissions("salesOrder221Ajax:*")
		.addToPermissions("salesOrder224Ajax:*")

        .addToPermissions("workflowApproval:check")
        .addToPermissions("workflowApproval:approval")

        .addToPermissions("report:run")
        .addToPermissions("report:view")

        .addToPermissions("outBound:index")
        .addToPermissions("outBound:list")
        .addToPermissions("outBound:show")
        .addToPermissions("outBound:resurrect")
        .addToPermissions("outBound:showXml")

        .addToPermissions("creditControl:index")
        .addToPermissions("creditControl:list")
        .addToPermissions("creditControl:show")
        
		.addToPermissions("search:index")
		.addToPermissions("search:search")
		.addToPermissions("search:searchProduct")
		.addToPermissions("search:searchContract")
		.addToPermissions("search:searchSalesOrder")
        
        .addToPermissions("menu:mySalesOrder")
        .addToPermissions("menu:mySalesOrderList")
        .addToPermissions("menu:b2b")
        .addToPermissions("menu:workflow")
        .addToPermissions("menu:mySalesOrder")
        .addToPermissions("menu:createSalesOrder")
		.addToPermissions("menu:reportList")
		.addToPermissions("menu:outBoundList")
		.addToPermissions("menu:search")
		.addToPermissions("menu:resetMyPassword")
	
		.addToPermissions("resetMyPassword:resetPassword")
		.addToPermissions("resetMyPassword:reset")

        .save(flush:true)     

        /*init dealer*/
        def ownerAH0001 = new User(username: "AH0001".toLowerCase(),  passwordHash: new Sha512Hash("AH0001").toHex(), profile:profile_dealer, role : role_dealer).save(flush:true)
        def dealerAH0001 = new Dealer(department:dept3450, serialNumber:'AH0001', name:'合肥博大电气有限公司', alias:'合肥博大电气', shortcut:'BDDQ', salesMan:staff10210, head:'侯卫民', contact:'夏秋霞', address1 : '安微省合肥市高新区望江西路539号鲲鹏科技园', address2 : '侯卫民 0551-5310128 13856002226', approvalStatus:'已核准', pricingMode:'折扣率', owner : ownerAH0001 ).save(flush:true)

        def ownerAH0002 = new User(username: "AH0002".toLowerCase(),  passwordHash: new Sha512Hash("AH0002").toHex(), profile:profile_dealer, role : role_dealer).save(flush:true)
        def dealerAH0002 = new Dealer(department:dept3450, serialNumber:'AH0002', name:'合肥越天电气自动化有限公司', alias:'合肥越天电气', shortcut:'YTDQ', salesMan:staff10210, head:'杜越天', contact:'杜越天', address1 : '合肥市濉溪东路14号', address2 : '', approvalStatus:'不准交易'     , pricingMode:'折扣率', owner : ownerAH0002  ).save(flush:true)
       
        def ownerAH0003 = new User(username: "AH0003".toLowerCase(),  passwordHash: new Sha512Hash("AH0003").toHex(), profile:profile_dealer, role : role_dealer).save(flush:true)
        def dealerAH0003 = new Dealer(department:dept3450, serialNumber:'AH0003', name:'合肥阳光电源有限公司', alias:'合肥阳光电源', shortcut:'YGDY', salesMan:staff10210, head:'', contact:'史大贵139056089127', address1 : '安徽省合肥市高新区天湖路2号采购部 史大贵139056089127', address2 : '', approvalStatus:'不准交易'     , pricingMode:'折扣率', owner : ownerAH0003  ).save(flush:true)
       
        def ownerAH0004 = new User(username: "AH0004".toLowerCase(),  passwordHash: new Sha512Hash("AH0004").toHex(), profile:profile_dealer, role : role_dealer).save(flush:true)
        def dealerAH0004 = new Dealer(department:dept3450, serialNumber:'AH0004', name:'安庆市良信电力设备有限责任公司', alias:'安庆良信电力', shortcut:'AQLXDL', salesMan:staff10210, head:'', contact:''     , address1 : '', address2 : '', approvalStatus:'已核准'     , pricingMode:'折扣率' , owner : ownerAH0004 ).save(flush:true)
       
        def ownerAH0005 = new User(username: "AH0005".toLowerCase(),  passwordHash: new Sha512Hash("AH0005").toHex(), profile:profile_dealer, role : role_dealer).save(flush:true)
        def dealerAH0005 = new Dealer(department:dept3450, serialNumber:'AH0005', name:'合肥宇嘉电气有限公司', alias:'合肥宇嘉电气', shortcut:'YJDQ', salesMan:staff10210, head:'石敏', contact:'石敏'     , address1 : '合肥市宿州路60号太阳城大厦615室', address2 : '', approvalStatus:'不准交易'     , pricingMode:'折扣率' , owner : ownerAH0005 ).save(flush:true)
       
        def ownerAH0006 = new User(username: "AH0006".toLowerCase(),  passwordHash: new Sha512Hash("AH0006").toHex(), profile:profile_dealer, role : role_dealer).save(flush:true)
        def dealerAH0006 = new Dealer(department:dept3450, serialNumber:'AH0006', name:'合肥竺禹商贸有限公司', alias:'合肥竺禹商贸', shortcut:'ZYSM', salesMan:staff10210, head:'余晞', contact:'余晞'     , address1 : '', address2 : '合肥市宁国路152号', approvalStatus:'不准交易'     , pricingMode:'折扣率' , owner : ownerAH0006 ).save(flush:true)
       
        def ownerAH0007 = new User(username: "AH0007".toLowerCase(),  passwordHash: new Sha512Hash("AH0007").toHex(), profile:profile_dealer, role : role_dealer).save(flush:true)
        def dealerAH0007 = new Dealer(department:dept3450, serialNumber:'AH0007', name:'安徽科维信息系统有限公司', alias:'安徽科维', shortcut:'', salesMan:staff10210, head:'', contact:'', address1 : '合肥市高新区天智路20号科大创新201室   0551-5327958/5325861', address2 : 'FAX:0551-5327968  张程敏 13966698260/陈智15155171767', approvalStatus:'不准交易'     , pricingMode:'折扣率' , owner : ownerAH0007 ).save(flush:true)
       
        def ownerAH0008 = new User(username: "AH0008".toLowerCase(),  passwordHash: new Sha512Hash("AH0008").toHex(), profile:profile_dealer, role : role_dealer).save(flush:true)
        def dealerAH0008 = new Dealer(department:dept3510, serialNumber:'AH0008', name:'安徽动力源科技有限公司', alias:'安徽动力源', shortcut:'AHDLY', salesMan:staff10210, head:'', contact:'', address1 : '安徽省宣城市郎溪县经济开发区安徽动力源科技有限公司 0563-7026968', address2 : '吴昆 13966197199 0563-2316821', approvalStatus:'已核准'     , pricingMode:'折扣率' , owner : ownerAH0008 ).save(flush:true)
       
        def ownerBJ0001 = new User(username: "BJ0001".toLowerCase(),  passwordHash: new Sha512Hash("BJ0001").toHex(), profile:profile_dealer, role : role_dealer).save(flush:true)
        def dealerBJ0001 = new Dealer(department:dept3510, serialNumber:'BJ0001', name:'北京茂程科贸有限公司', alias:'北京茂程科贸', shortcut:'MCKM', salesMan:staff10210, head:'', contact:'经理程旭', address1 : '北京市海淀区中关村大街E世界A区1138室', address2 : '提货人：汲怀磊 010-82387299-602/13261788805', approvalStatus:'已核准'     , pricingMode:'折扣率', owner : ownerBJ0001  ).save(flush:true)
       
        def ownerBJ0002 = new User(username: "BJ0002".toLowerCase(),  passwordHash: new Sha512Hash("BJ0002").toHex(), profile:profile_dealer, role : role_dealer).save(flush:true)
        def dealerBJ0002 = new Dealer(department:dept3510, serialNumber:'BJ0002', name:'北京索普尼科技有限公司', alias:'北京索普尼', shortcut:'SPN', salesMan:staff10210, head:'冯西平', contact:'赵鉴华', address1 : '', address2 : '', approvalStatus:'不准交易'     , pricingMode:'' , owner : ownerBJ0002 ).save(flush:true)
       
        def ownerBJ0003 = new User(username: "BJ0003".toLowerCase(),  passwordHash: new Sha512Hash("BJ0003").toHex(), profile:profile_dealer, role : role_dealer).save(flush:true)
        def dealerBJ0003 = new Dealer(department:dept3510, serialNumber:'BJ0003', name:'北京东方博飞电力技术有限责任公司', alias:'北京东方博飞', shortcut:'DFBF', salesMan:staff10210, head:'冯西平', contact:'刘建中', address1 : '', address2 : '', approvalStatus:'不准交易'     , pricingMode:'' , owner : ownerBJ0003 ).save(flush:true)
       
        def ownerBJ0004 = new User(username: "BJ0004".toLowerCase(),  passwordHash: new Sha512Hash("BJ0004").toHex(), profile:profile_dealer, role : role_dealer).save(flush:true)
        def dealerBJ0004 = new Dealer(department:dept3510, serialNumber:'BJ0004', name:'北京动力源科技股份有限公司', alias:'动力源', shortcut:'DLY', salesMan:staff10210, head:'赖华明', contact:'采购田源 010-63795656', address1 : '提货人：汲怀磊 010-82387299-602/13261788805', address2 : '赖华明  13601038908', approvalStatus:'已核准'     , pricingMode:'' , owner : ownerBJ0004 ).save(flush:true)
       
        def ownerBJ0005 = new User(username: "BJ0005".toLowerCase(),  passwordHash: new Sha512Hash("BJ0005").toHex(), profile:profile_dealer, role : role_dealer).save(flush:true)
        def dealerBJ0005 = new Dealer(department:dept3510, serialNumber:'BJ0005', name:'北京联鸿电器有限公司', alias:'北京联鸿电器', shortcut:'LHDQ', salesMan:staff10210, head:'董江峰', contact:'董江峰', address1 : '北京市顺义区俸伯北区4号楼2门301', address2 : '董江峰 010-89476873  (到货顺义）', approvalStatus:'不准交易'     , pricingMode:'' , owner : ownerBJ0005 ).save(flush:true)
       
        def ownerSH0013 = new User(username: "SH0013".toLowerCase(),  passwordHash: new Sha512Hash("SH0013").toHex(), profile:profile_dealer, role : role_dealer).save(flush:true)
        def dealerSH0013 = new Dealer(department:dept3310, serialNumber:'SH0013', name:'浙江海得电气实业有限公司', alias:'浙江海得电气', shortcut:null, salesMan:staff10854, head:'', contact:'', address1 : '桐乡经济开发区三期工业区人民南路东厂房2楼海得运营仓库  0573－80881368', address2 : '联系人:王宏燕 0573-80881367 13819412011', approvalStatus:'已核准', pricingMode:'' , owner : ownerSH0013 ).save(flush:true)
       
        def ownerGD0020 = new User(username: "GD0020".toLowerCase(),  passwordHash: new Sha512Hash("GD0020").toHex(), profile:profile_dealer, role : role_dealer).save(flush:true)
        def dealerGD0020 = new Dealer(department:dept3220, serialNumber:'GD0020', name:'东莞市昊泰电器有限公司', alias:'东莞昊泰', shortcut:'HT', salesMan:staff13073, head:'', contact:'', address1 : '东莞市东城区竹主山鸿怡花园C座3D-307  0769-22667676', address2 : '张孝祥  0769-22667676   13018608615', approvalStatus:'已核准', pricingMode:'', owner : ownerGD0020  ).save(flush:true)
	
        /*init contract*/
        def recordType21 = new RecordType (serialNumber : '221', name : '行业特价申请', domain : 'contract',  isActive : true, description : '').save(flush:true)
        def recordType22 = new RecordType (serialNumber : '224', name : '非行业特价申请', domain : 'contract',  isActive : true, description : '').save(flush:true)
       /*init industry*/
        def channelGK0001 = new Industry( serialNumber:"GK0001", shortName:"工控", fullName:"工业控制", isActive:true, description:"与工业设备配套的控制系统行业。如机床设备、机械设备、电梯设备等。").save(flush:true)
        new Industry(serialNumber:"GY0008", shortName:"铁路", fullName:"铁路", isActive:false, description:"包含动车、地铁、电力机车、城际列车、车载空调、铁路系统等").save(flush:true)
        def channelGY1001 = new Industry(serialNumber:"GY1001", shortName:"电信", fullName:"电信", isActive:true, description:"包含通讯电源、电讯设备").save(flush:true)
        new Industry(serialNumber:"ID9999", shortName:"其他", fullName:"其他", isActive:false, description:"上述不使用的其他行业").save(flush:true)
        new Industry(serialNumber:"JZGJ01", shortName:"建筑", fullName:"建筑", isActive:true, description:"包含工业厂房（冶金、石化、水泥等）配套及改建").save(flush:true)
        new Industry(serialNumber:"JZMJ01", shortName:"公建/住宅", fullName:"公建/住宅", isActive:false, description:"包含政府、事业单位如机关、学校、医院等兴建的各类项目以及各类商业住宅、民用住宅等房产行业").save(flush:true)
        new Industry(serialNumber:"NYDL01", shortName:"电力", fullName:"电力", isActive:true, description:"指各级电力系统、电业局等").save(flush:true)
        new Industry(serialNumber:"NYDL02", shortName:"电力电源", fullName:"电力电源", isActive:true, description:"包含继电保护、变电站自动化、配电自动化等").save(flush:true)
        new Industry(serialNumber:"OEM", shortName:"OEM", fullName:"OEM", isActive:false, description:"指我司为其他公司代工的情况").save(flush:true)


        /*Category*/
        def recordType11 = new RecordType (serialNumber : '1', name : '会计', domain : 'category',  isActive : true, description : '').save(flush:true)
        def recordType12 = new RecordType (serialNumber : '2', name : '仓管', domain : 'category',  isActive : true, description : '').save(flush:true)
        def recordType13 = new RecordType (serialNumber : '3', name : '产品', domain : 'category',  isActive : true, description : '').save(flush:true)
        def recordType14 = new RecordType (serialNumber : '4', name : '客户', domain : 'category',  isActive : true, description : '').save(flush:true)
        def recordType15 = new RecordType (serialNumber : '5', name : '属性', domain : 'category',  isActive : true, description : '').save(flush:true)
        
        def category11_01 = new Category (recordType : recordType11, serialNumber : '01', name : '零部件', discount : 0, productionCycle:0, transportCycle:0, isActive:true).save(flush:true)
        def category11_02 = new Category (recordType : recordType11, serialNumber : '02', name : '辅料包材', discount : 0, productionCycle:0, transportCycle:0, isActive:true).save(flush:true)
        def category11_04 = new Category (recordType : recordType11, serialNumber : '04', name : '产成品', discount : 0, productionCycle:0, transportCycle:0, isActive:true).save(flush:true)
        def category11_09 = new Category (recordType : recordType11, serialNumber : '09', name : '外购成品', discount : 0, productionCycle:0, transportCycle:0, isActive:true).save(flush:true)

        def category12_001 = new Category (recordType : recordType12, serialNumber : '001', name : 'NDM1-63', discount : 0, productionCycle:2, transportCycle:5, isActive:true).save(flush:true)
        def category12_001A = new Category (recordType : recordType12, serialNumber : '001A', name : 'NDM1A-63', discount : 0,productionCycle:2, transportCycle:5, isActive:true).save(flush:true)
        def category12_001B = new Category (recordType : recordType12, serialNumber : '001B', name : 'NDB1C-63', discount : 0,productionCycle:2, transportCycle:5, isActive:true).save(flush:true)
        def category12_002 = new Category (recordType : recordType12, serialNumber : '002', name : 'NDM1-125', discount : 0, productionCycle:2, transportCycle:5, isActive:true).save(flush:true)
        def category12_002A = new Category (recordType : recordType12, serialNumber : '002A', name : 'NDM1A-125', discount : 0, productionCycle:2, transportCycle:5, isActive:true).save(flush:true)
        def category12_003 = new Category (recordType : recordType12, serialNumber : '003', name : 'NDM1L-32', discount : 0, productionCycle:2, transportCycle:5, isActive:true).save(flush:true)
        def category12_031 = new Category (recordType : recordType12, serialNumber : '031', name : 'NDB1GQ', discount : 0, productionCycle:2, transportCycle:5, isActive:true).save(flush:true)
        def category12_032 = new Category (recordType : recordType12, serialNumber : '032', name : 'NDM1GQ', discount : 0, productionCycle:2, transportCycle:5, isActive:true).save(flush:true)
        def category12_103 = new Category (recordType : recordType12, serialNumber : '103', name : 'NDW1-2000', discount : 0, productionCycle:2, transportCycle:5, isActive:true).save(flush:true)
        def category12_1011 = new Category (recordType : recordType12, serialNumber : '1011', name : 'NDM2-225', discount : 0, productionCycle:2, transportCycle:5, isActive:true).save(flush:true)

        def category13_A01 = new Category (recordType : recordType13, serialNumber : 'A01', name : 'NDM1-63系列', discount : 0,productionCycle:0, transportCycle:0, isActive:true).save(flush:true)
        def category13_A02 = new Category (recordType : recordType13, serialNumber : 'A02', name : 'NDM1-125系列', discount : 0,productionCycle:0, transportCycle:0, isActive:true).save(flush:true)
        def category13_A03 = new Category (recordType : recordType13, serialNumber : 'A03', name : 'NDB1-32系列', discount : 0, productionCycle:0, transportCycle:0, isActive:true).save(flush:true)
        def category13_A04 = new Category (recordType : recordType13, serialNumber : 'A04', name : 'NDB1L-32系列', discount : 0, productionCycle:0, transportCycle:0, isActive:true).save(flush:true)
        def category13_A05 = new Category (recordType : recordType13, serialNumber : 'A05', name : 'NDB2-63系列', discount : 0, productionCycle:0, transportCycle:0, isActive:true).save(flush:true)
        def category13_A06 = new Category (recordType : recordType13, serialNumber : 'A06', name : 'NDM1L-3/5/10', discount : 0,productionCycle:0, transportCycle:0, isActive:true).save(flush:true)
        def category13_A09 = new Category (recordType : recordType13, serialNumber : 'A09', name : 'NDM2系列', discount : 0, productionCycle:0, transportCycle:0, isActive:true).save(flush:true) 
        def category13_A16 = new Category (recordType : recordType13, serialNumber : 'A16', name : 'NDW1-系列', discount : 0,productionCycle:0, transportCycle:0, isActive:true).save(flush:true)

        def category14_B01 = new Category (recordType : recordType14, serialNumber : 'B01', name : '良信', discount : 0, productionCycle:0, transportCycle:0, isActive:true).save(flush:true)
        def category14_B12 = new Category (recordType : recordType14, serialNumber : 'B12', name : 'OEM-ETA', discount : 0, productionCycle:0, transportCycle:0, isActive:true).save(flush:true)
        def category14_B13 = new Category (recordType : recordType14, serialNumber : 'B13', name : 'OEM-VIKO', discount : 0, productionCycle:0, transportCycle:0, isActive:true).save(flush:true)
        def category14_B14 = new Category (recordType : recordType14, serialNumber : 'B14', name : 'OEM-固安捷', discount : 0, productionCycle:0, transportCycle:0, isActive:true).save(flush:true)
        def category14_B15 = new Category (recordType : recordType14, serialNumber : 'B15', name : 'OEM-松下', discount : 0, productionCycle:0, transportCycle:0, isActive:true).save(flush:true)
        def category14_B99 = new Category (recordType : recordType14, serialNumber : 'B99', name : '外购成品', discount : 0, productionCycle:0, transportCycle:0, isActive:true).save(flush:true)
                
        def category15_001 = new Category (recordType : recordType15, serialNumber : '001', name : '主件（小断）', discount : 0.62, productionCycle:0, transportCycle:0, isActive:true, isAllowSpecialDiscount:true, isAllowZeroPrice : true).save(flush:true)

        def category15_002 = new Category (recordType : recordType15, serialNumber : '002', name : '附件（小断）', discount : 0.62,productionCycle:0, transportCycle:0, isActive:true, isAllowSpecialDiscount:true, isAllowZeroPrice : true).save(flush:true)

        def category15_003 = new Category (recordType : recordType15, serialNumber : '003', name : '主附件(小断)', discount : 0.62,productionCycle:0, transportCycle:0,hasBom:true, isActive:true, isAllowSpecialDiscount:true, isAllowZeroPrice : true).save(flush:true)

        def category15_004 = new Category (recordType : recordType15, serialNumber : '004', name : '主件(高分断)', discount : 0.65,productionCycle:0, transportCycle:0, isActive:true, isAllowSpecialDiscount:true, isAllowZeroPrice : true).save(flush:true)
        def category15_005 = new Category (recordType : recordType15, serialNumber : '005', name : '附件(高分断)', discount : 0.65,productionCycle:0, transportCycle:0, isActive:true, isAllowSpecialDiscount:true, isAllowZeroPrice : true).save(flush:true)
        def category15_011 = new Category (recordType : recordType15, serialNumber : '011', name : '本体(朔壳)', discount : 0.625,productionCycle:0, transportCycle:0, hasBom:true, isActive:true, isAllowSpecialDiscount:true, isAllowZeroPrice : true).save(flush:true)
        def category15_012 = new Category (recordType : recordType15, serialNumber : '012', name : '附件(朔壳)', discount : 0.625,productionCycle:0, transportCycle:0, hasBom:true, isActive:true, isAllowZeroPrice : true).save(flush:true)
        def category15_013 = new Category (recordType : recordType15, serialNumber : '013', name : '本附件(朔壳)', discount : 0.625,productionCycle:0, transportCycle:0, hasBom:true, isActive:true, isAllowZeroPrice : true).save(flush:true)
        def category15_015 = new Category (recordType : recordType15, serialNumber : '015', name : '本体(框架)', discount : 0.7,productionCycle:0, transportCycle:0, isActive:true, isAllowSpecialDiscount:true).save(flush:true)
        def category15_016 = new Category (recordType : recordType15, serialNumber : '016', name : '附件(框架)', discount : 1,productionCycle:0, transportCycle:0, isActive:true, isAllowZeroPrice : true).save(flush:true)
        def category15_017 = new Category (recordType : recordType15, serialNumber : '017', name : '本附件(框架)', discount : 0.7,productionCycle:0, transportCycle:0,hasBom:true, isActive:true, isAllowZeroPrice : true).save(flush:true)
        def category15_027 = new Category (recordType : recordType15, serialNumber : '027', name : 'NDB1C系列', discount : 0.6,productionCycle:0, transportCycle:0,hasBom:false, isActive:true, isAllowSpecialDiscount:true, isAllowZeroPrice : true).save(flush:true)
        def category15_999 = new Category (recordType : recordType15, serialNumber : '999', name : '零部件单供', discount : 1,productionCycle:0, transportCycle:0,hasBom:true, isActive:true, isAllowZeroPrice : true).save(flush:true)

        //bom主附件定价 220
        def p3600096 = new Product(serialNumber:'3600096', name:'NDW1智能型万能式断路器', standard:'NDW1-SZ32(IC1600A/4/K3H32S2/Q23/M)', unit : '套', price : 0, isIncludeTax: true, isActive:true)
            .addToCategories(new ProductCategory(category : category11_04))
            .addToCategories(new ProductCategory(category : category12_103))
            .addToCategories(new ProductCategory(category : category13_A16))
            .addToCategories(new ProductCategory(category : category14_B01))
            .addToCategories(new ProductCategory(category : category15_017))
            .save(flush:true)
            
        def p20001708 = new Product(serialNumber:'20001708', name:'框架虚设附件', standard:'NDW1 门框(框I/抽屉式)', unit : '套', price : 100, isIncludeTax: true, isActive:true)
            .addToCategories(new ProductCategory(category : category11_01))
            .addToCategories(new ProductCategory(category : category12_103))
            .addToCategories(new ProductCategory(category : category15_016))
            .save(flush:true)
            
        def p20001711 = new Product(serialNumber:'20001711', name:'框架虚设附件', standard:'NDW1 欠压延时脱扣器（框I/AC220V/可调）', unit : '套', price : 600, isIncludeTax: true, isActive:true)
            .addToCategories(new ProductCategory(category : category11_01))
            .addToCategories(new ProductCategory(category : category12_103))
            .addToCategories(new ProductCategory(category : category15_016))
            .save(flush:true)
            
        def p20001761 = new Product(serialNumber:'20001761', name:'框架虚设附件', standard:'NDW1 三锁两钥匙', unit : '套', price : 1000, isIncludeTax: true, isActive:true)
            .addToCategories(new ProductCategory(category : category11_01))
            .addToCategories(new ProductCategory(category : category12_103))
            .addToCategories(new ProductCategory(category : category15_016))
            .save(flush:true)
            
        def p20002236 = new Product(serialNumber:'20002236', name:'框架本体（虚设）', standard:'NDW1-2000C 1600A/4/K3H32S2/D2F2B2', unit : '台', price : 24347.4, isIncludeTax: true, isActive:true)
            .addToCategories(new ProductCategory(category : category11_01))
            .addToCategories(new ProductCategory(category : category12_103))
            .addToCategories(new ProductCategory(category : category15_015))
            .save(flush:true)
        def bomp3600096 = new Bom(product:p3600096)
            .addToBomDetails(new BomDetail(serialNumber: '0010', product:p20001708, dosage:3, quota : 1))
            .addToBomDetails(new BomDetail(serialNumber: '0020', product:p20001711, dosage:3, quota : 1))
            .addToBomDetails(new BomDetail(serialNumber: '0030', product:p20001761, dosage:1, quota : 1))
            .addToBomDetails(new BomDetail(serialNumber: '0040', product:p20002236, dosage:3, quota : 1))
            .save(flush:true)

        //221
        def p3008245 = new Product(serialNumber:'3008245', name:'过欠压断路器', standard:'NDM1GQ-50 C40/4（NDB1C-63）', unit : '台', price : 0, isIncludeTax: false, isActive:true)
            .addToCategories(new ProductCategory(category : category11_04))
            .addToCategories(new ProductCategory(category : category12_032))
            .addToCategories(new ProductCategory(category : category13_A06))
            .addToCategories(new ProductCategory(category : category14_B01))
            .addToCategories(new ProductCategory(category : category15_003))
            .save(flush:true)
        def p18300257 = new Product(serialNumber:'18300257', name:'NDM1CL-32/50罩 RoHS', standard:'8LD.300.257 4P', unit : '根', price : 0, isIncludeTax: false, isActive:true)
            .addToCategories(new ProductCategory(category : category11_01))
            .addToCategories(new ProductCategory(category : category12_032))
            .save(flush:true)

        def p18907103 = new Product(serialNumber:'18907103', name:'NDM1L-32/50漏电拼装盘头螺钉', standard:'GB823-88 M3*25', unit : '根', price : 0.070200, isIncludeTax: true, isActive:true)
            .addToCategories(new ProductCategory(category : category11_01))
            .addToCategories(new ProductCategory(category : category12_003))
            .addToCategories(new ProductCategory(category : category13_A06))
            .addToCategories(new ProductCategory(category : category15_999))
            .save(flush:true)
        def p1890710316 = new Product(serialNumber:'1890710316', name:'NDM1L-32拼装螺钉73.2mm RoHS', standard:'8LD.907.103.16', unit : '根', price : 0, isIncludeTax: false, isActive:true)
            .addToCategories(new ProductCategory(category : category11_01))
            .addToCategories(new ProductCategory(category : category12_003))
            .save(flush:true)

                    
        def p3008804 = new Product(serialNumber:'3008804', name:'小型断路器 RoHS', standard:'NDB1C-63 C40/4', unit : '台', price : 123.300000, isIncludeTax: true, isActive:true)
            .addToCategories(new ProductCategory(category : category11_04))
            .addToCategories(new ProductCategory(category : category12_001B))
            .addToCategories(new ProductCategory(category : category13_A01))
            .addToCategories(new ProductCategory(category : category14_B01))
            .addToCategories(new ProductCategory(category : category15_027))
            .save(flush:true)

        def p3008017 = new Product(serialNumber:'3008017', name:'过欠压脱扣器 RoHS', standard:'NDM1GQ-50/4P （NDB1C-63）', unit : '台', price : 0, isIncludeTax: true, isActive:true)
            .addToCategories(new ProductCategory(category : category11_04))
            .addToCategories(new ProductCategory(category : category12_031))
            .addToCategories(new ProductCategory(category : category13_A06))
            .addToCategories(new ProductCategory(category : category14_B01))
            .addToCategories(new ProductCategory(category : category15_001))
            .save(flush:true)
        def bom3008245 = new Bom(product:p3008245)
            .addToBomDetails(new BomDetail(serialNumber: '0010', product:p18300257, dosage:1, quota : 1))
            .addToBomDetails(new BomDetail(serialNumber: '0020', product:p18907103, dosage:1, quota : 1))
            .addToBomDetails(new BomDetail(serialNumber: '0030', product:p1890710316, dosage:2, quota : 1))
            .addToBomDetails(new BomDetail(serialNumber: '0040', product:p3008804, dosage:1, quota : 1))
            .addToBomDetails(new BomDetail(serialNumber: '0050', product:p3008017, dosage:1, quota : 1))
            .save(flush:true)
			
			
        def contractYX24Z201108030005 = new Contract(
            serialNumber:'YX24Z201108030005'
            , recordType:recordType21
            , subject : '夏弗纳-行业大客户申请表'
            , applicant:'张柳'
            , filingDate : new Date()
            , effectiveDate:new Date()
            , dealer : dealerSH0013
            , project : '夏弗纳'
            , industry : channelGY1001
            , description : '夏弗纳电磁容是行业大客户，生产产品与埃德一样，主要生产滤波模块。目前有一个大唐项目使用我司NDB3-100 Z2/1LSA2A0R产品。经过前期技术确认和商务沟通，我们的报价略高于埃德，需要申请特价支持，请刘经理给予支持。'
            , createdBy : user_admin
            , lastModifiedBy:user_admin
            , owner:ownerSH0013)
            .addToContractDetails(
                new ContractDetail(serialNumber:'1', category : category12_103, discount : 0.62, specialDiscount: 0.32, finalDiscount:0.42, expiryDate : new Date() + 180)
            ).save(flush:true)
            
        /*221 非行业特价申请单*/
        def contractYX09G201108190041 = new Contract(
            serialNumber:'YX09G201108190041'
            , recordType:recordType22
            , subject : '东莞昊泰-雍景豪苑-非行业大客户申请表'
            , applicant:'刘华丽'
            , filingDate : new Date()
            , effectiveDate:new Date() 
            , dealer : dealerGD0020
            , project : '雍景豪苑'
            , industry : channelGK0001
            , description : '雍景地产是东莞区域重点房地产项目之一，其目前开发了雍景豪苑等住宅小区，并开始分批采购我司产品，预计采购量30万左右，现向公司申请5个大点的议价，请公司领导给予支持分盼，非常感谢！'
            , createdBy : user_admin
            , lastModifiedBy:user_admin
            , owner:ownerGD0020).addToContractDetails(
                new ContractDetail(serialNumber:'1', category : category12_032, discount : 0.625, specialDiscount: 0.08, finalDiscount:0.575, expiryDate : new Date() + 180)
            ).addToContractDetails(
                new ContractDetail(serialNumber:'2', category : category12_001B, discount : 0.625, specialDiscount: 0.10, finalDiscount:0.5625, expiryDate : new Date() + 180)
            ).save(flush:true)

        def contractYX09G201108190042 = new Contract(
            serialNumber:'YX09G201108190042'
            , recordType:recordType22
            , subject : '东莞昊泰-雍景豪苑-非行业大客户申请表'
            , applicant:'刘华丽'
            , filingDate : new Date()
            , effectiveDate:new Date()
            , dealer : dealerGD0020
            , project : '雍景豪苑'
            , industry : channelGK0001
            , description : '雍景地产是东莞区域重点房地产项目之一，其目前开发了雍景豪苑等住宅小区，并开始分批采购我司产品，预计采购量30万左右，现向公司申请5个大点的议价，请公司领导给予支持分盼，非常感谢！'
            , createdBy :user_admin 
            , lastModifiedBy:user_admin
            , owner:ownerGD0020)
            .addToContractDetails(
                new ContractDetail(serialNumber:'1', category : category12_031, discount : 0.625, specialDiscount: 0.12, finalDiscount:0.55, expiryDate : new Date() + 180)
            ).addToContractDetails(
                new ContractDetail(serialNumber:'2', category : category12_001B, discount : 0.625, specialDiscount: 0.12, finalDiscount:0.55, expiryDate : new Date() + 180)
            ).save(flush:true)

        def contractYX09G201108190043 = new Contract(
            serialNumber:'YX09G201108190043'
            , recordType:recordType21
            , subject : '东莞昊泰-雍景豪苑-行业大客户申请表'
            , applicant:'刘华丽'
            , filingDate : new Date()
            , effectiveDate:new Date()
            , dealer : dealerGD0020
            , project : '雍景豪苑'
            , industry : channelGK0001
            , description : '雍景地产是东莞区域重点房地产项目之一，其目前开发了雍景豪苑等住宅小区，并开始分批采购我司产品，预计采购量30万左右，现向公司申请5个大点的议价，请公司领导给予支持分盼，非常感谢！'
            , createdBy : user_admin
            , lastModifiedBy:user_admin
            , owner:ownerGD0020).addToContractDetails(
                new ContractDetail(serialNumber:'1', category : category12_031, discount : 0.60, specialDiscount: 0.2, finalDiscount:0.48, expiryDate : new Date() + 180)
            ).addToContractDetails(
                new ContractDetail(serialNumber:'2', category : category12_001B, discount : 0.60, specialDiscount: 0.01, finalDiscount:0.594, expiryDate : new Date() + 180)
            ).save(flush:true)

        //出站消息配置--订单
        new ObConfig(
            objectName:'salesOrder'
            , method : 'transferSalesOrder'
            , asynchronous : false 
            , priority : 1
            , defaultSendErrorLimit : 1
            , defaultGetErrorLimit : 1
        ).save(flush:true)
        //出站消息配置--信用额
        new ObConfig(
            objectName:'dealer'
            , method : 'creditControl'
            , asynchronous : false
            , priority : 2
            , defaultSendErrorLimit : 1
            , defaultGetErrorLimit : 1
        ).save(flush:true)
/*
log.info("3210=${new Sha512Hash('Nj7o1Lc34').toHex()}")
log.info("GD0016=${new Sha512Hash('tongxi163').toHex()}")
log.info("GD0024=${new Sha512Hash('hite1234').toHex()}")
log.info("GD0032=${new Sha512Hash('13609063881lx').toHex()}")
log.info("ST0001=${new Sha512Hash('ndD5Q7A98').toHex()}")
log.info("3220=${new Sha512Hash('Wxi@16682').toHex()}")
log.info("GD0020=${new Sha512Hash('224682700').toHex()}")
log.info("GD0026=${new Sha512Hash('ndc3S081W').toHex()}")
log.info("GD0035=${new Sha512Hash('19820930').toHex()}")
log.info("3230=${new Sha512Hash('10052@ZS').toHex()}")
log.info("GD0010=${new Sha512Hash('ndVbV0JHnQ').toHex()}")
log.info("GD0033=${new Sha512Hash('AA123456').toHex()}")
log.info("3240=${new Sha512Hash('fuzhou16407').toHex()}")
log.info("FJ0007=${new Sha512Hash('fzsjjd001002').toHex()}")
log.info("FJ0015=${new Sha512Hash('fzjxdz888').toHex()}")
log.info("3250=${new Sha512Hash('10080@sz2').toHex()}")
log.info("SZ0008=${new Sha512Hash('12345678').toHex()}")
log.info("SZ0017=${new Sha512Hash('yx1733536554').toHex()}")
log.info("3260=${new Sha512Hash('Myself@0103').toHex()}")
log.info("GD0034=${new Sha512Hash('ndV3DSAsR7').toHex()}")
log.info("GX0001=${new Sha512Hash('7712507806').toHex()}")
log.info("3270=${new Sha512Hash('Ngdgoaf13').toHex()}")
log.info("GD0014=${new Sha512Hash('81236272').toHex()}")
log.info("GD0036=${new Sha512Hash('FsGB0001dq').toHex()}")
log.info("3280=${new Sha512Hash('123456').toHex()}")
log.info("3310=${new Sha512Hash('16531@shgk').toHex()}")
log.info("SH0013=${new Sha512Hash('hite1234').toHex()}")
log.info("SH0037=${new Sha512Hash('ndLi27cxu').toHex()}")
log.info("SH0039=${new Sha512Hash('sh22443570').toHex()}")
log.info("SH0042=${new Sha512Hash('guangrui123').toHex()}")
log.info("SH0043=${new Sha512Hash('SHDKSW57631970').toHex()}")
log.info("3320=${new Sha512Hash('1545615456').toHex()}")
log.info("SH0019=${new Sha512Hash('58143044').toHex()}")
log.info("SH0125=${new Sha512Hash('AO228228').toHex()}")
log.info("3330=${new Sha512Hash('hz13239.,').toHex()}")
log.info("ZJ0021=${new Sha512Hash('nd3ptHnobo').toHex()}")
log.info("ZJ0024=${new Sha512Hash('sh89900638').toHex()}")
log.info("ZJ0034=${new Sha512Hash('10000000').toHex()}")
log.info("ZJ0035=${new Sha512Hash('nd02bKFU48').toHex()}")
log.info("ZJ0036=${new Sha512Hash('ndD6PS2f4').toHex()}")
log.info("3340=${new Sha512Hash('1545615456').toHex()}")
log.info("SH0008=${new Sha512Hash('ndn3Q33Y02').toHex()}")
log.info("3350=${new Sha512Hash('84448876').toHex()}")
log.info("XI0005=${new Sha512Hash('ndzUQqhxe').toHex()}")
log.info("XI0009=${new Sha512Hash('yang1112').toHex()}")
log.info("XI0010=${new Sha512Hash('nd0iUadUKx').toHex()}")
log.info("XI0026=${new Sha512Hash('nd2203965').toHex()}")
log.info("3360=${new Sha512Hash('16001@nc').toHex()}")
log.info("JS0046=${new Sha512Hash('ndbpr3ay0').toHex()}")
log.info("JX0001=${new Sha512Hash('ndy61Dct4').toHex()}")
log.info("3410=${new Sha512Hash('83479160').toHex()}")
log.info("JS0012=${new Sha512Hash('ndEjajWWFM').toHex()}")
log.info("JS0045=${new Sha512Hash('ndDW7Jb6W').toHex()}")
log.info("JS0047=${new Sha512Hash('100866666').toHex()}")
log.info("3420=${new Sha512Hash('12471@sxcjz').toHex()}")
log.info("JS0004=${new Sha512Hash('wuxitianshi').toHex()}")
log.info("JS0010=${new Sha512Hash('nd31rAub9').toHex()}")
log.info("JS0025=${new Sha512Hash('88876699').toHex()}")
log.info("JS0032=${new Sha512Hash('67571867').toHex()}")
log.info("JS0042=${new Sha512Hash('3153150000').toHex()}")
log.info("JS0044=${new Sha512Hash('88876699').toHex()}")
log.info("3430=${new Sha512Hash('63691366').toHex()}")
log.info("HN0002=${new Sha512Hash('djy7654321').toHex()}")
log.info("HN0009=${new Sha512Hash('nd73yps25').toHex()}")
log.info("HN0011=${new Sha512Hash('ndaQEfQ57e').toHex()}")
log.info("HN0015=${new Sha512Hash('13838383631yx').toHex()}")
log.info("HN0016=${new Sha512Hash('123456789').toHex()}")
log.info("HN0018=${new Sha512Hash('ndQYZKx0z').toHex()}")
log.info("HN0019=${new Sha512Hash('ndL4SiH193').toHex()}")
log.info("HN0020=${new Sha512Hash('12345678').toHex()}")
log.info("HN0021=${new Sha512Hash('60925115').toHex()}")
log.info("3440=${new Sha512Hash('59501427').toHex()}")
log.info("EE0003=${new Sha512Hash('87596216').toHex()}")
log.info("EE0035=${new Sha512Hash('VSTKJ8888').toHex()}")
log.info("EE0036=${new Sha512Hash('EE0036N@der').toHex()}")
log.info("3450=${new Sha512Hash('gqm760422').toHex()}")
log.info("AH0005=${new Sha512Hash('13965077241').toHex()}")
log.info("JS0041=${new Sha512Hash('ZCJDSB2861928').toHex()}")
log.info("LN0037=${new Sha512Hash('nd62H3cUPw').toHex()}")
log.info("3460=${new Sha512Hash('16780@xz').toHex()}")
log.info("JS0024=${new Sha512Hash('16780@xz').toHex()}")
log.info("3510=${new Sha512Hash('82386038').toHex()}")
log.info("BJ0021=${new Sha512Hash('hn334561').toHex()}")
log.info("BJ0023=${new Sha512Hash('nd568hTGe').toHex()}")
log.info("BJ0025=${new Sha512Hash('moyan222208').toHex()}")
log.info("3520=${new Sha512Hash('13336@tj').toHex()}")
log.info("TJ0010=${new Sha512Hash('ndor6Igh58').toHex()}")
log.info("TJ0011=${new Sha512Hash('ndbz2iqzoK').toHex()}")
log.info("TJ0013=${new Sha512Hash('13802165386').toHex()}")
log.info("3530=${new Sha512Hash('10204@jn').toHex()}")
log.info("SD0009=${new Sha512Hash('ndc80Zf6kw').toHex()}")
log.info("SD0012=${new Sha512Hash('jinanxinfu').toHex()}")
log.info("SD0013=${new Sha512Hash('nd90uH9RWx').toHex()}")
log.info("SD0016=${new Sha512Hash('lxjd88589179.').toHex()}")
log.info("SD0028=${new Sha512Hash('jnsh2381991').toHex()}")
log.info("SD0063=${new Sha512Hash('jn88677555').toHex()}")
log.info("SD0114=${new Sha512Hash('ky3581822').toHex()}")
log.info("3540=${new Sha512Hash('31186974698').toHex()}")
log.info("HB0009=${new Sha512Hash('gdjd86684385').toHex()}")
log.info("HB0017=${new Sha512Hash('ndq4jQ02C').toHex()}")
log.info("HB0018=${new Sha512Hash('nd2U6DEuj').toHex()}")
log.info("HB0039=${new Sha512Hash('yhjd3041268').toHex()}")
log.info("3550=${new Sha512Hash('3515256992').toHex()}")
log.info("SX0006=${new Sha512Hash('13633420361').toHex()}")
log.info("TY0001=${new Sha512Hash('3517622578').toHex()}")
log.info("TY0002=${new Sha512Hash('nd0r1G17r').toHex()}")
log.info("TY0003=${new Sha512Hash('ndC1bsF7hC').toHex()}")
log.info("TY0004=${new Sha512Hash('shanxifenxi').toHex()}")
log.info("3560=${new Sha512Hash('15035135474').toHex()}")
log.info("NMG0001=${new Sha512Hash('ndGRT6iDn7').toHex()}")
log.info("3570=${new Sha512Hash('17043@qd').toHex()}")
log.info("SD0004=${new Sha512Hash('HDL148668').toHex()}")
log.info("SD0010=${new Sha512Hash('123698745').toHex()}")
log.info("3580=${new Sha512Hash('NkOP6rvbQ27').toHex()}")
log.info("HB0001=${new Sha512Hash('3152326950').toHex()}")
log.info("3610=${new Sha512Hash('NQso273H8ZI').toHex()}")
log.info("LN0026=${new Sha512Hash('cjf8532223').toHex()}")
log.info("LN0046=${new Sha512Hash('123456789').toHex()}")
log.info("3620=${new Sha512Hash('15610hrb').toHex()}")
log.info("HLJ0004=${new Sha512Hash('XB18645112927').toHex()}")
log.info("HLJ0005=${new Sha512Hash('LS114566').toHex()}")
log.info("3630=${new Sha512Hash('NDP9f9CL6DY').toHex()}")
log.info("LN0036=${new Sha512Hash('ndmCJeEpu9').toHex()}")
log.info("LN0038=${new Sha512Hash('nd2mq776F').toHex()}")
log.info("LN0039=${new Sha512Hash('nd6U2FpC79').toHex()}")
log.info("3640=${new Sha512Hash('N29j1P46rt').toHex()}")
log.info("LN0033=${new Sha512Hash('nd6Fk1JB9').toHex()}")
log.info("LN0035=${new Sha512Hash('nd2a48D9a').toHex()}")
log.info("LN0045=${new Sha512Hash('nd3W35V8SR').toHex()}")
log.info("3710=${new Sha512Hash('cqb67745931').toHex()}")
log.info("SC0003=${new Sha512Hash('15998983149').toHex()}")
log.info("SC0010=${new Sha512Hash('ndr75bugBB').toHex()}")
log.info("SC0011=${new Sha512Hash('nd93Mx6JT').toHex()}")
log.info("SC0012=${new Sha512Hash('63020675').toHex()}")
log.info("SC0031=${new Sha512Hash('13368145354').toHex()}")
log.info("3720=${new Sha512Hash('12345678910').toHex()}")
log.info("CD0004=${new Sha512Hash('SSHZ2012').toHex()}")
log.info("CD0009=${new Sha512Hash('xinpuda86080090').toHex()}")
log.info("CD0012=${new Sha512Hash('99999999').toHex()}")
log.info("3730=${new Sha512Hash('11106lxj@').toHex()}")
log.info("SX0007=${new Sha512Hash('ndjvpmE5iZ').toHex()}")
log.info("SX0008=${new Sha512Hash('ndO04Mmq9q').toHex()}")
log.info("SX0010=${new Sha512Hash('88325667').toHex()}")
log.info("SX0034=${new Sha512Hash('sxtydq86690320').toHex()}")
log.info("3740=${new Sha512Hash('Guiyangban201011!').toHex()}")
log.info("GZ0002=${new Sha512Hash('2012chuandian1343601086').toHex()}")
log.info("GZ0003=${new Sha512Hash('ndBOUrmv3').toHex()}")
log.info("3750=${new Sha512Hash('19850301').toHex()}")
log.info("YN0005=${new Sha512Hash('yxlc5749439').toHex()}")
log.info("YN0008=${new Sha512Hash('6666666666').toHex()}")
log.info("3760=${new Sha512Hash('NmxgO75MU').toHex()}")
log.info("SX0009=${new Sha512Hash('ndJQAS7972').toHex()}")
log.info("3770=${new Sha512Hash('xf24830385').toHex()}")
log.info("XJ0001=${new Sha512Hash('ndfwRteLbA').toHex()}")
log.info("SZ0009=${new Sha512Hash('ndG3lEzRea').toHex()}")
log.info("test1=${new Sha512Hash('123').toHex()}")
log.info("test2=${new Sha512Hash('1234').toHex()}")
log.info("test=${new Sha512Hash('test').toHex()}")
log.info("SZ0011=${new Sha512Hash('sq6Dvkc').toHex()}")
log.info("commercial=${new Sha512Hash('N@der098').toHex()}")
log.info("SD0034=${new Sha512Hash('SD0034qdb').toHex()}")
log.info("JS0048=${new Sha512Hash('Gp4M93').toHex()}")
log.info("SH0045=${new Sha512Hash('59505466').toHex()}")
log.info("SD0017=${new Sha512Hash('18805390577').toHex()}")
log.info("SX0011=${new Sha512Hash('hpdq8781555').toHex()}")
log.info("BJ0026=${new Sha512Hash('BJJFD2910').toHex()}")
log.info("SD0018=${new Sha512Hash('wwf616wwf616').toHex()}")
log.info("SD0019=${new Sha512Hash('SD0019qdb').toHex()}")
log.info("HKS001=${new Sha512Hash('253589333').toHex()}")
log.info("XI0011=${new Sha512Hash('P@55#%39der').toHex()}")
log.info("SC0014=${new Sha512Hash('cqxz161460').toHex()}")
log.info("LN0040=${new Sha512Hash('Du452$&df#d9').toHex()}")
log.info("JX0002=${new Sha512Hash('P@tt326scd').toHex()}")
log.info("JS0049=${new Sha512Hash('86510768').toHex()}")
log.info("GZ0004=${new Sha512Hash('chzhm760205').toHex()}")
log.info("EE0026=${new Sha512Hash('hite1234').toHex()}")
log.info("GD0037=${new Sha512Hash('GD2013$11am#@7').toHex()}")
log.info("TY0005=${new Sha512Hash('Mi2013@der&%1708').toHex()}")
*/
	}
    def destroy = {
    }
}
