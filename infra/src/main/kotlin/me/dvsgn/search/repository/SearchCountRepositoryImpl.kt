package me.dvsgn.search.repository

import me.dvsgn.search.document.SearchCountDoc
import me.dvsgn.search.domain.SearchCount
import org.springframework.data.domain.Sort
import org.springframework.data.mongodb.core.BulkOperations
import org.springframework.data.mongodb.core.MongoTemplate
import org.springframework.data.mongodb.core.query.Criteria
import org.springframework.data.mongodb.core.query.Query
import org.springframework.data.mongodb.core.query.Update
import org.springframework.stereotype.Repository

@Repository
class SearchCountRepositoryImpl(
    private val mongoTemplate: MongoTemplate
): SearchCountRepository {
    override fun count(query: String) {
        val bulkOps = mongoTemplate.bulkOps(BulkOperations.BulkMode.UNORDERED, SearchCountDoc::class.java)
        val dbQuery = Query(Criteria.where("query").`is`(query))
        val update = Update().inc("count", 1)
        bulkOps.upsert(dbQuery, update)
        bulkOps.execute()
    }

    override fun findTop10SearchKeywords(): List<SearchCount> {
        val query = Query()
            .with(Sort.by(Sort.Order.desc("count")))
            .limit(10)
        return mongoTemplate.find(query, SearchCountDoc::class.java)
            .map { it.toDomain() }
    }
}