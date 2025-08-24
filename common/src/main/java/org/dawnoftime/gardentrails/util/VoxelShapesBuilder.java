package org.dawnoftime.gardentrails.util;

import net.minecraft.world.level.block.Block;
import net.minecraft.world.phys.shapes.Shapes;
import net.minecraft.world.phys.shapes.VoxelShape;

public class VoxelShapesBuilder {

    protected static VoxelShape[] makePlateShapes() {
        final VoxelShape vsNorthFlat = Block.box(0.0D, 0.0D, 0.0D, 16.0D, 16.0D, 8.0D);
        final VoxelShape vsEastFlat = Block.box(8.0D, 0.0D, 0.0D, 16.0D, 16.0D, 16.0D);
        final VoxelShape vsSouthFlat = Block.box(0.0D, 0.0D, 8.0D, 16.0D, 16.0D, 16.0D);
        final VoxelShape vsWestFlat = Block.box(0.0D, 0.0D, 0.0D, 8.0D, 16.0D, 16.0D);
        final VoxelShape vsNorthWestCorner = Block.box(0.0D, 0.0D, 0.0D, 8.0D, 16.0D, 8.0D);
        final VoxelShape vsNorthEastCorner = Block.box(8.0D, 0.0D, 0.0D, 16.0D, 16.0D, 8.0D);
        final VoxelShape vsSouthEastCorner = Block.box(8.0D, 0.0D, 8.0D, 16.0D, 16.0D, 16.0D);
        final VoxelShape vsSouthWestCorner = Block.box(0.0D, 0.0D, 8.0D, 8.0D, 16.0D, 16.0D);
        return new VoxelShape[]{vsNorthWestCorner, vsNorthFlat, Shapes.or(vsNorthFlat, vsSouthWestCorner), vsNorthEastCorner, vsEastFlat, Shapes.or(vsEastFlat, vsNorthWestCorner), vsSouthEastCorner, vsSouthFlat, Shapes.or(vsSouthFlat, vsNorthEastCorner), vsSouthWestCorner, vsWestFlat, Shapes.or(vsWestFlat, vsSouthEastCorner),};
    }

    protected static VoxelShape[] makeEdgeShapes() {
        final VoxelShape vsNorthFlat = Block.box(0.0D, 0.0D, 0.0D, 16.0D, 8.0D, 8.0D);
        final VoxelShape vsEastFlat = Block.box(8.0D, 0.0D, 0.0D, 16.0D, 8.0D, 16.0D);
        final VoxelShape vsSouthFlat = Block.box(0.0D, 0.0D, 8.0D, 16.0D, 8.0D, 16.0D);
        final VoxelShape vsWestFlat = Block.box(0.0D, 0.0D, 0.0D, 8.0D, 8.0D, 16.0D);
        final VoxelShape vsNorthWestCorner = Block.box(0.0D, 0.0D, 0.0D, 8.0D, 8.0D, 8.0D);
        final VoxelShape vsNorthEastCorner = Block.box(8.0D, 0.0D, 0.0D, 16.0D, 8.0D, 8.0D);
        final VoxelShape vsSouthEastCorner = Block.box(8.0D, 0.0D, 8.0D, 16.0D, 8.0D, 16.0D);
        final VoxelShape vsSouthWestCorner = Block.box(0.0D, 0.0D, 8.0D, 8.0D, 8.0D, 16.0D);
        VoxelShape[] voxelShapes = new VoxelShape[24];
        VoxelShape[] voxelShapesBottom = {
                vsNorthWestCorner,
                vsNorthFlat,
                Shapes.or(vsNorthFlat, vsSouthWestCorner),
                vsNorthEastCorner,
                vsEastFlat,
                Shapes.or(vsEastFlat, vsNorthWestCorner),
                vsSouthEastCorner,
                vsSouthFlat,
                Shapes.or(vsSouthFlat, vsNorthEastCorner),
                vsSouthWestCorner,
                vsWestFlat,
                Shapes.or(vsWestFlat, vsSouthEastCorner)
        };
        System.arraycopy(voxelShapesBottom, 0, voxelShapes, 0, voxelShapesBottom.length);
        for (int i = 0; i < voxelShapesBottom.length; i++) {
            voxelShapes[i + voxelShapesBottom.length] = voxelShapesBottom[i].move(0.0D, 0.5D, 0.0D);
        }
        return voxelShapes;
    }

    protected static VoxelShape[] makeIvyShapes() {
        VoxelShape vsSouth = Block.box(0.0D, 0.0D, 12.0D, 16.0D, 16.0D, 16.0D);
        VoxelShape vsWest = Block.box(0.0D, 0.0D, 0.0D, 4.0D, 16.0D, 16.0D);
        VoxelShape vsNorth = Block.box(0.0D, 0.0D, 0.0D, 16.0D, 16.0D, 4.0D);
        VoxelShape vsEast = Block.box(12.0D, 0.0D, 0.0D, 16.0D, 16.0D, 16.0D);
        VoxelShape vsSouthWest = Shapes.or(vsSouth, vsWest);
        VoxelShape vsNorthWest = Shapes.or(vsWest, vsNorth);
        VoxelShape vsNorthEst = Shapes.or(vsNorth, vsEast);
        VoxelShape vsSouthEst = Shapes.or(vsEast, vsSouth);
        return new VoxelShape[]{
                Shapes.or(vsSouthWest, vsNorthEst),
                vsSouth,
                vsWest,
                vsSouthWest,
                vsNorth,
                Shapes.or(vsSouth, vsNorth),
                vsNorthWest,
                Shapes.or(vsSouthWest, vsNorth),
                vsEast,
                vsSouthEst,
                Shapes.or(vsWest, vsEast),
                Shapes.or(vsSouthWest, vsEast),
                vsNorthEst,
                Shapes.or(vsSouth, vsNorthEst),
                Shapes.or(vsNorthWest, vsEast),
        };
    }
}
