package org.leaf.eos2.ws

class YifeiEncodeService {

    static transactional = false 

    def encode(source) {
        def result = "${source}"
            result = result.replaceAll(/&/, '&amp;')
            result = result.replaceAll(/</, '&lt;')
            result = result.replaceAll(/>/, '&gt;')
            result = result.replaceAll(/"/, '&quot;')
            result = result.replaceAll(/\'/, '&apos;')
            result = result.replaceAll(/\|/, '&stdd;')
        return result
    }

    def decode(source){
        def result = "${source}"
            result = result.replaceAll(/&amp;/, '&')
            result = result.replaceAll(/&lt;/, '<')
            result = result.replaceAll(/&gt;/, '>')
            result = result.replaceAll(/&quot;/, '"')
            result = result.replaceAll(/&apos;/, "'")
            result = result.replaceAll(/&stdd;/, '|')
        return result
    }
}
