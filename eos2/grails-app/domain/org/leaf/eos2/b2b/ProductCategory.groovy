package org.leaf.eos2.b2b

class ProductCategory {

    Product product
    Category category
    
    static belongsTo = [product : Product]

    static constraints = {
        product(nullable:false)
        category(nullable:false, unique:'product')
    }
    
    String toString() {
        return "$category.name"
    }
    
    static mapping = {
        table 'B2B_PRODUCTCATEGORY'
    }
}
