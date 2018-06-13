clc;
% Code to plot simulation results from sh_well_jet_pump
%% Plot Description:
%
% The plots below show flow rates into and out of the jet pump and the
% volume of the water tank.
%
% Copyright 2015 The MathWorks, Inc.

% Generate simulation results if they don't exist
% if ~exist('simlog_sh_well_jet_pump', 'var')
%     sim('sh_well_jet_pump')
% end


% Generate simulation results if they don't exist
if ~exist('simlog_sh_well_jet_pump', 'var')
    sim('wasserpumpe')
end

% Reuse figure if it exists, else create new figure
if ~exist('h1_wasserpumpe', 'var') || ...
        ~isgraphics(h1_wasserpumpe, 'figure')
    h1_wasserpumpe = figure('Name', 'wasserpumpe');
end

figure(h1_wasserpumpe)
clf(h1_wasserpumpe)

% Get simulation results
simlog_t = simlog_sh_well_jet_pump.Jet_Pump.q_1.series.time;
simlog_tankv = simlog_sh_well_jet_pump.Tank.tank.volume.series.values('gal');
simlog_welljqA = simlog_sh_well_jet_pump.Jet_Pump.q_1.series.values('gpm');
simlog_welljqS = simlog_sh_well_jet_pump.Jet_Pump.q_2.series.values('gpm');
simlog_welljqP = simlog_sh_well_jet_pump.Jet_Pump.q_pump.series.values('gpm');
simlog_tankq = simlog_sh_well_jet_pump.Pipe_Tank.flow_rate.series.values('gpm');

% Plot results
simlog_handles(1) = subplot(2, 1, 1);
plot(simlog_t, simlog_tankv, 'LineWidth', 3)
grid on
title('Volume of Water in Tank')
ylabel('Volume (gallons)')

simlog_handles(2) = subplot(2, 1, 2);
plot(simlog_t, simlog_tankq, 'LineWidth', 3)
hold on
plot(simlog_t, simlog_welljqP, 'LineWidth', 1)
plot(simlog_t, simlog_welljqS, 'LineWidth', 2, 'LineStyle','--')
plot(simlog_t, simlog_welljqA, 'LineWidth', 1)
hold off
grid on
title('Jet Pump Flow Rates')
ylabel('Flow Rates (gpm)')
legend({'Tank Inlet','Jet Pump Outlet','Jet Pump Primary Inlet','Jet Pump Secondary Inlet'},'Location','Best');
xlabel('Time (s)')
set(gca,'YLim',[0 200]);

linkaxes(simlog_handles, 'x')

% Remove temporary variables
clear simlog_t simlog_handles
clear simlog_welljqP simlog_tankq simlog_welljqS simlog_tankv simlog_welljqA 