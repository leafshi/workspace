package org.leaf.eos2.sys

import org.springframework.transaction.annotation.*
import org.hibernate.FetchMode as FM

import org.leaf.eos2.b2b.Product
import org.leaf.eos2.b2b.Contract
import org.leaf.eos2.b2b.SalesOrder

class SearchService {

    @Transactional(readOnly = true)
    def searchProduct(term) {
        return Product.withCriteria{
            or{
                ilike("serialNumber", term + "%")
                ilike("standard", term + "%")
            }
            maxResults(10)
            order("serialNumber", "asc")
        }
    }
    
    @Transactional(readOnly = true)
    def searchContract(term) {
        return Contract.withCriteria{
            or{
                ilike("serialNumber", term + "%")
            }
            maxResults(10)
            order("serialNumber", "asc")
        }
    } 
    
    @Transactional(readOnly = true)
    def searchSalesOrder(term) {
        return SalesOrder.withCriteria{
            or{
            	eq("id", term.isInteger()? Long.valueOf(term) : -1L)
                ilike("serialNumber", term + "%")
            }
            maxResults(10)
            order("serialNumber", "asc")
        }
    } 
}