package snapshotcycle.wintersquared.entity;

import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.ai.goal.Goal;
import org.jspecify.annotations.Nullable;

import java.util.EnumSet;

public class ModSwellGoal extends Goal {
    private final ShiverEntity shiver;
    private @Nullable LivingEntity target;

    public ModSwellGoal(final ShiverEntity shiver) {
        this.shiver = shiver;
        this.setFlags(EnumSet.of(Goal.Flag.MOVE));
    }

    @Override
    public boolean canUse() {
        LivingEntity target = this.shiver.getTarget();
        return this.shiver.getSwellDir() > 0 || target != null && !target.isDeadOrDying() && this.shiver.distanceToSqr(target) < 9.0;
    }

    @Override
    public void start() {
        this.shiver.getNavigation().stop();
        this.target = this.shiver.getTarget();
    }

    @Override
    public void stop() {
        this.target = null;
    }

    @Override
    public boolean requiresUpdateEveryTick() {
        return true;
    }

    @Override
    public void tick() {
        if (this.target != null && !this.target.isDeadOrDying()) {
            if (this.shiver.distanceToSqr(this.target) > 49.0) {
                this.shiver.setSwellDir(-1);
            } else if (!this.shiver.getSensing().hasLineOfSight(this.target)) {
                this.shiver.setSwellDir(-1);
            } else {
                this.shiver.setSwellDir(1);
            }
        } else {
            this.shiver.setSwellDir(-1);
        }
    }
}