package hk.edu.polyu.comp.comp2021.clevis.model.shapes;

/**
 * An interface for checking intersect.
 *
 * @author Ho Man Hin
 */
public interface IntersectSegment {
    /**
     * Checks if the given segment intersects this segment.
     *
     * @param intersectSegment the segment to check against
     * @return whether the two intersect
     */
    boolean isIntersect(IntersectSegment intersectSegment);
}
