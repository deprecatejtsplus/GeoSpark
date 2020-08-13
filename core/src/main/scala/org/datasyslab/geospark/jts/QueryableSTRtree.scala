package org.datasyslab.geospark.jts

import java.util

import com.vividsolutions.jts.geom.Envelope
import com.vividsolutions.jts.index.strtree.{AbstractNode, STRtree}

/**
 * A class that adds query bounds to STRtree
 */
class QueryableSTRtree(nodeCapacity: Int) extends STRtree(nodeCapacity) {
    override def queryBoundary(): util.ArrayList[Envelope] = {
        build()
        val boundaries = new util.ArrayList[Envelope]()
        queryBoundary_rec(root, boundaries)
        boundaries
    }

    private def queryBoundary_rec(node: AbstractNode, matches: util.List[Envelope]): Unit = {
        if (node.getLevel == 0) matches.add(node.getBounds.asInstanceOf[Envelope])
        else node.getChildBoundables forEach {
            case abstractNode: AbstractNode => queryBoundary_rec(abstractNode, matches)
        }
    }
}
