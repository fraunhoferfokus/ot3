package targetcode;

import java.io.BufferedInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.HashMap;
import java.util.Map;

import org.etsi.ttcn.tci.TciBehaviourId;
import org.etsi.ttcn.tci.TciModuleId;
import org.etsi.ttcn.tci.TciModuleIdList;
import org.etsi.ttcn.tci.TciModuleParameterList;
import org.etsi.ttcn.tci.TciParameterList;
import org.etsi.ttcn.tci.TciParameterTypeList;
import org.etsi.ttcn.tci.TciTMRequired;
import org.etsi.ttcn.tci.TciTestCaseId;
import org.etsi.ttcn.tci.TciTestCaseIdList;
import org.etsi.ttcn.tci.Value;
import org.etsi.ttcn.tci.TciTestCaseId;

import org.etsi.ttcn.tri.TriComponentId;
import org.etsi.ttcn.tri.TriPortIdList;

import de.fraunhofer.fokus.ttcn.tci.TciTestCaseIdListImpl;
import de.fraunhofer.fokus.ttcn.tci.TciTestCaseIdImpl;

import targetcode.Trace;

import java.lang.reflect.Method;
import java.lang.reflect.InvocationTargetException;

import java.util.List;
import java.util.ArrayList;

public class XciTMRequiredImpl implements TciTMRequired {

	Class<?> kl;
	Object obj;
	MetaModule metaobj;

	// -------------------------------------------------------------------------
	private static String dasmodul = "dasmodul";

	@Override
	public void tciRootModule(TciModuleId moduleName) {
		try {
			String str = moduleName.getModuleName();
			dasmodul = str;

			Class myclass = null;

			CustomClassLoader cclassLoader = new CustomClassLoader();

			str = str + ".MODULE";
			kl = Class.forName(str);

			obj = kl.newInstance();

			metaobj = (MetaModule) obj;

		} catch (Exception E) {
			System.out.println(E);
			Throwable E2 = ((InvocationTargetException) E).getTargetException();
			System.out.println(E2);
			E2.printStackTrace();
		}
	}

	// -------------------------------------------------------------------------

	@Override
	public TciModuleIdList tciGetImportedModules() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public TciModuleParameterList tciGetModuleParameters(TciModuleId moduleId) {
		// TODO Auto-generated method stub
		return null;
	}

	// -------------------------------------------------------------------------
	@Override
	public TciTestCaseIdList tciGetTestCases() {

		List<TciTestCaseId> L = new ArrayList<TciTestCaseId>();

		String names[] = metaobj.GetTestCases();
		for (String str : names) {
			TciTestCaseId Entry = new TciTestCaseIdImpl(dasmodul, str);
			L.add(Entry);
		}
		TciTestCaseIdList List = new TciTestCaseIdListImpl(L);
		return List;
	}

	// -------------------------------------------------------------------------

	@Override
	public TciParameterTypeList tciGetTestCaseParameters(
			TciTestCaseId TestCaseId) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public TriPortIdList tciGetTestCaseTSI(TciTestCaseId testCaseId) {
		// TODO Auto-generated method stub
		return null;
	}

	// -------------------------------------------------------------------------
	@Override
	public void tciStartTestCase(final String testCaseId,
			final TciParameterList parameterList) {

		if (Instructions.TC_RUNNING) {
			Instructions.FATAL("TC_RUNNING");
		}
		Instructions.TC_RUNNING = true;

		try {
			Thread Thr = new Thread(new Runnable() {
				@Override
				public void run() {
					Instructions.StartThread();
					try {
						Instructions.SetFirstVerdict();
						iStartTestCase(testCaseId, parameterList);
					} catch (TestCaseKilled X) {
						Instructions
								.FATAL("Shit: TestCaseKilled caught in run/StartTestCase");
					} catch (TtcnFatal X) {

						// Instructions.FATAL("Shit: TtcnFatal caught in run/StartTestCase");
						Instructions.SetErrorVerdict();
					} catch (Exception X) {
						Instructions
								.FATAL("Shit: Exception caught in run/StartTestCase");
					}
					Instructions.EndThread();
				}
			});

			TestCaseThread = Thr;
			Thr.start();

			try {
				Thr.join();
			} catch (Exception E) {
				System.out.println("wait error !!! "+ E);
				E.printStackTrace();
				//Instructions.FATAL("Exception caught in wait/StartTestCase");
			}

		} catch (Exception E) {
			System.out.println("exception !!! " + E);
			E.printStackTrace();
			//Instructions.FATAL("*** EXCEPTION IN StartTestCase ***");
		}
		Instructions.TC_RUNNING = false;
		Instructions.ReportThreads();

	}

	private static Thread TestCaseThread = null;

	private void iStartTestCase(String testCaseId,
			TciParameterList parameterList) {
		Method pong = null;
		try {
			Class[] argTypes = new Class[] {};
			pong = kl.getDeclaredMethod(testCaseId, argTypes);
			if (pong == null) {
				Instructions.FATAL("TciTMRequired: pong == null");
			}
		} catch (Exception E) {
			Instructions.FATAL("TciTMRequired: Testcase not found");
		}

		try {
			pong.invoke(obj);
		} catch (TtcnError X) {
			Instructions.FATAL("TtcnError caught in iStartTestCase");
		} catch (TestCaseKilled X) {
			Instructions.FATAL("TestCaseKilled caught in iStartTestCase");
		} catch (TtcnFatal E) {
			Instructions.FATAL("TtcnFatal caught in iStartTestCase");
		} catch (Exception E) {
			Throwable E2 = ((InvocationTargetException) E).getTargetException();
			// E2.printStackTrace();
			Instructions.FATAL("Exception caught in iStartTestCase");
		}

	}

	// -------------------------------------------------------------------------

	@Override
	public void tciStopTestCase() {
		// TODO Auto-generated method stub

	}

	// -------------------------------------------------------------------------
	@Override
	public TriComponentId tciStartControl() {
		try {
			Class[] argTypes = new Class[] {};
			Method pong = kl.getDeclaredMethod("RunControlPart", argTypes);
			pong.invoke(obj);

		} catch (Exception E) {
			System.out.println(E);
			Throwable E2 = ((InvocationTargetException) E).getTargetException();
			System.out.println(E2);
			E2.printStackTrace();
		}
		return null;
	}

	// -------------------------------------------------------------------------

	@Override
	public void tciStopControl() {
		// TODO Auto-generated method stub

	}

	@Override
	public void tciStartConfig(TciBehaviourId configId,
			TciParameterList parameterList) {
		// TODO Auto-generated method stub

	}

	@Override
	public void tciKillConfig(Value ref) {
		// TODO Auto-generated method stub

	}

}

// hier
/**
 * 
 * @author http://codeslices.net team
 * 
 */

/**
 * 
 * Simple custom class loader implementation
 * 
 */
class CustomClassLoader extends ClassLoader {

	/**
	 * The HashMap where the classes will be cached
	 */
	private Map<String, Class<?>> classes = new HashMap<String, Class<?>>();

	@Override
	public String toString() {
		return CustomClassLoader.class.getName();
	}

	public Class<?> myfindClass(String name) throws ClassNotFoundException {
		return findClass(name);
	}

	@Override
	protected Class<?> findClass(String name) throws ClassNotFoundException {

		if (classes.containsKey(name)) {
			return classes.get(name);
		}

		byte[] classData;

		try {
			classData = loadClassData(name);
		} catch (IOException e) {
			throw new ClassNotFoundException("Class [" + name
					+ "] could not be found", e);
		}

		Class<?> c = defineClass(name, classData, 0, classData.length);
		resolveClass(c);
		classes.put(name, c);

		return c;
	}

	/**
	 * Load the class file into byte array
	 * 
	 * @param name
	 *            The name of the class e.g. com.codeslices.test.TestClass
	 * @return The class file as byte array
	 * @throws IOException
	 */
	private byte[] loadClassData(String name) throws IOException {
		// System.out.println("einlesen der class xxxxxxxxxxxxxxxxxxxxxxxxxx");
		BufferedInputStream in = new BufferedInputStream(
				ClassLoader.getSystemResourceAsStream(name.replace(".", "/")
						+ ".class"));
		ByteArrayOutputStream out = new ByteArrayOutputStream();
		int i;

		while ((i = in.read()) != -1) {
			out.write(i);
		}

		in.close();
		byte[] classData = out.toByteArray();
		out.close();

		return classData;
	}

	/**
	 * Simple usage of the CustomClassLoader implementation
	 * 
	 * @param args
	 * @throws ClassNotFoundException
	 * @throws IllegalAccessException
	 * @throws InstantiationException
	 * @throws SecurityException
	 * @throws NoSuchMethodException
	 * @throws InvocationTargetException
	 * @throws IllegalArgumentException
	 */
	public static void main(String[] args) throws ClassNotFoundException,
			InstantiationException, IllegalAccessException,
			NoSuchMethodException, SecurityException, IllegalArgumentException,
			InvocationTargetException {
		CustomClassLoader loader = new CustomClassLoader();
		// This class should be in your application class path
		Class<?> c = loader.findClass("net.codeslices.test.TestClass");
		Object o = c.newInstance();
		Method m = c.getMethod("toString");
		System.out.println(m.invoke(o));
	}

}
