package event_horizon.model;

import org.hibernate.engine.spi.SharedSessionContractImplementor;
import org.hibernate.type.SqlTypes;
import org.hibernate.usertype.UserType;

import java.io.Serializable;
import java.sql.*;
import java.util.Objects;

public class PointType implements UserType<Point> {

    @Override
    public int getSqlType() {
        return SqlTypes.POINT;
    }

    @Override
    public Class<Point> returnedClass() {
        return Point.class;
    }

    @Override
    public boolean equals(Point x, Point y) {
        if (x == null || y == null) {
            return false;
        }

        if (x == y) {
            return true;
        }

        if (x.getClass() != y.getClass() && hashCode(x) != hashCode(y)) {
            return false;
        }

        return x.getX() == y.getX() && x.getZ() == y.getZ();
    }

    @Override
    public int hashCode(Point x) {
        return Objects.hashCode(x);
    }

    @Override
    public Point nullSafeGet(ResultSet rs, int position, SharedSessionContractImplementor session, Object owner) throws SQLException {
        String pointData = rs.getString(position);
        if (pointData == null) {
            return null;
        }

        String[] parts = pointData.split(",");
        if (parts.length != 2) {
            throw new SQLException("Invalid point data at position " + position);
        }

        double x = Double.parseDouble(parts[0]);
        double z = Double.parseDouble(parts[1]);

        return new Point(x, z);
    }


    @Override
    public void nullSafeSet(PreparedStatement st, Point value, int index, SharedSessionContractImplementor session) throws SQLException {
        if (value == null) {
            st.setNull(index, SqlTypes.VARCHAR);
        }
        else {
            String pointString = value.getX() + "," + value.getZ();
            st.setString(index, pointString);
        }
    }

    @Override
    public Point deepCopy(Point value) {
        return value == null ? null : new Point(value.getX(), value.getZ());
    }

    @Override
    public boolean isMutable() {
        return true;
    }

    @Override
    public Serializable disassemble(Point value) {
        if (value == null) {
            return null;
        }

        return new double[]{value.getX(), value.getZ()};
    }

    @Override
    public Point assemble(Serializable cached, Object owner) {
        double[] cache = (double[]) cached;

        return new Point(cache[0], cache[1]);
    }
}
